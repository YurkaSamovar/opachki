package org.example.database.repository;

import org.example.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface UserRepository extends JpaRepository<User, Integer>,
        FilterUserRepository, QuerydslPredicateExecutor<User> {

}
