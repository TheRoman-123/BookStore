package com.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Table(name = "language")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Language {
    @EmbeddedId
    private LanguageCode languageCode;

    @ManyToOne(optional = false)
    @MapsId("bookId") // maps bookId attribute of embedded id
    private Book book;

    public String languageCode() {
        return languageCode.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Language language = (Language) o;
        return getLanguageCode() != null && Objects.equals(getLanguageCode(), language.getLanguageCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(languageCode);
    }
}
