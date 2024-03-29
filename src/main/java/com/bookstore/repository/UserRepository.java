package com.bookstore.repository;

import com.bookstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    User findByUsername(String username);

    Optional<User> findByEmail(String email);

}
