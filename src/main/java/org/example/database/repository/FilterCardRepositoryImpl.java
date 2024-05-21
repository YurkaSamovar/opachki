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
                .add(filter.theme(), card.theme.id::eq)
                .add(filter.date(), card.date::eq)
                .add(filter.prix(), prix -> card.prix.between(0, prix))
                .build();

        return new JPAQuery<Card>(entityManager)
                .select(card)
                .from(card)
                .where(predicate)
                .fetch();
    }
}
