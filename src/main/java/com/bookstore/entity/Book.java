package com.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "book")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Book implements Serializable {
    @Serial
    private static final long serialVersionUID = 68548L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "book_title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "in_stock_number", nullable = false, columnDefinition = "int default 0")
    private Integer inStockNumber;

    @Column(name = "price")
    private Float price;

    @Column(name = "publication_date", columnDefinition = "DATE")
    private LocalDate publicationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    @ToString.Exclude
    private Publisher publisher;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "book")
    @ToString.Exclude
    private Set<Language> languages;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return getId() != null && Objects.equals(getId(), book.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
