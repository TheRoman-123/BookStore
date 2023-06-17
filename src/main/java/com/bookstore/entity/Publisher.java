package com.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "publisher")
@Getter
@Setter
@ToString
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "publisher_id", nullable = false)
    private Long id;

    @Column(name = "publisher_name", unique = true)
    private String publisherName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
    @ToString.Exclude
    private Set<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Publisher publisher = (Publisher) o;
        return getId() != null && Objects.equals(getId(), publisher.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
