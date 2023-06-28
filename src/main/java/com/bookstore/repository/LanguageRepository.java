package com.bookstore.repository;

import com.bookstore.entity.Language;
import com.bookstore.entity.LanguageCode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, LanguageCode> {
}
