package com.bookstore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "order_status")
@Getter
@Setter
@ToString
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "order_status_id", nullable = false)
    private Long id;

    @Column(name = "payment_status", nullable = false, columnDefinition = "boolean default false")
    private Boolean paymentStatus;

    @Column(name = "delivery_status", nullable = false, columnDefinition = "boolean default false")
    private Boolean deliveryStatus;

    @Column(name = "update_status_date", nullable = false, columnDefinition = "TIMESTAMP")
    private LocalDateTime updateStatusDate;

    @OneToOne(mappedBy = "orderStatus")
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderStatus orderStatus = (OrderStatus) o;
        return getId() != null && Objects.equals(getId(), orderStatus.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
