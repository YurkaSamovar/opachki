package org.example.database.repository;

import org.example.database.entity.User;
import org.example.service.mapper.UserCreateEditMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>,
        FilterUserRepository, QuerydslPredicateExecutor<User> {

    Optional<User> findByUsername(String username);
}
