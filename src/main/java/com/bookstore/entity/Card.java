package com.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "card")
@Getter
@Setter
@ToString
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "card_id", nullable = false)
    private Long id;

    @Column(name = "card_number", length = 16, nullable = false, unique = true, columnDefinition = "CHAR")
    private String cardNumber;

    @Column(name = "holder_name", nullable = false)
    private String holderName;

    @Column(name = "expiry_month", length = 2, nullable = false, columnDefinition = "CHAR")
    private String expiryMonth;

    @Column(name = "expiry_year", length = 4, nullable = false)
    private String expiryYear;

    @Column(name = "cvc_code", length = 3, nullable = false, columnDefinition = "CHAR")
    private String cvcCode;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
    @ToString.Exclude
    private Set<Order> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Card card = (Card) o;
        return getId() != null && Objects.equals(getId(), card.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}