package com.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "literary_work")
@Getter
@Setter
@ToString
public class LiteraryWork {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "literary_work_id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "year_written")
    private Short yearWritten;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    @ToString.Exclude
    private Author author;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "book_literary_work",
            joinColumns = @JoinColumn(name = "literary_work_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @ToString.Exclude
    private Set<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        LiteraryWork that = (LiteraryWork) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
