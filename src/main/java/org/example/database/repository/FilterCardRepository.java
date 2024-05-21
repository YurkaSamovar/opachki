package org.example.database.repository;

import org.example.database.entity.Card;
import org.example.database.entity.User;
import org.example.dto.CardFilter;
import org.example.dto.UserFilter;

import java.util.List;

public interface FilterCardRepository {

    List<Card> findAllByFilter(CardFilter filter);
}
