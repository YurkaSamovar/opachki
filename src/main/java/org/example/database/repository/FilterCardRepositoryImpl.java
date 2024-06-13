package org.example.database.repository;

import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.example.database.entity.Card;
import org.example.database.entity.User;
import org.example.dto.CardFilter;
import org.example.dto.QPredicates;

import java.util.List;

import static org.example.database.entity.QCard.card;

@RequiredArgsConstructor
public class FilterCardRepositoryImpl implements FilterCardRepository {

    private final EntityManager entityManager;

    @Override
    public List<Card> findAllByFilter(CardFilter filter) {
        var predicate = QPredicates.builder()
                .add(filter.theme(), theme -> {
                    if(theme == 0)
                        return card.theme.id.goe(0);
                    else
                        return card.theme.id.eq(theme);
                } )
                .add(filter.date(), card.date::eq)
                .add(filter.prix(), prix -> {
                    if(prix < 0)
                        return card.prix.goe(0);
                    else
                        return card.prix.loe(prix);
                })
                .build();

        return new JPAQuery<Card>(entityManager)
                .select(card)
                .from(card)
                .where(predicate)
                .fetch();
    }
}
