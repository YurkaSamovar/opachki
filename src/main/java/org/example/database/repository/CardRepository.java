package org.example.database.repository;

import org.example.database.entity.Card;
import org.example.dto.CardFilter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer>, FilterCardRepository {
}
