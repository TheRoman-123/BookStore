package com.bookstore.repository;

import com.bookstore.entity.LiteraryWork;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiteraryWorkRepository extends JpaRepository<LiteraryWork, Long> {
    boolean existsByTitleAndAuthorId(String title, Long authorId);
}
