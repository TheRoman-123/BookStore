package com.bookstore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LanguageCode implements Serializable {
    @Serial
    private static final long serialVersionUID = 54810L;

    @Column(name = "language_code", nullable = false, length = 2, columnDefinition = "CHAR")
    private String languageCode; // two characters representation of each language

//    @Column(name = "book_id", nullable = false)
    private Long bookId; // foreign key to Book entity

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LanguageCode that = (LanguageCode) o;
        return languageCode != null && Objects.equals(languageCode, that.languageCode)
                && bookId != null && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageCode, bookId);
    }
}
