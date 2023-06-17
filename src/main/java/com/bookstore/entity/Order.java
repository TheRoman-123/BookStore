package com.bookstore.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "order")
@Getter
@Setter
@ToString
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 5442982158L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_id", nullable = false)
    private Long id;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "payment_method", length = 9, nullable = false)
    private String paymentMethod; // cash, card on, card off

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_status_id")
    @ToString.Exclude
    private OrderStatus orderStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    @ToString.Exclude
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address_id")
    @ToString.Exclude
    private DeliveryAddress deliveryAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Order order = (Order) o;
        return getId() != null && Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
