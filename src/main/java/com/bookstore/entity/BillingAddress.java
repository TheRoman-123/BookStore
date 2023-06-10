package com.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class BillingAddress implements Serializable {
    @Serial
    private static final long serialVersionUID = 454521256L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String billingAddressName;

    private String billingAddressStreet;

    private String billingAddressCity;

    @OneToOne
    @JsonIgnore
    private Order order;
}
