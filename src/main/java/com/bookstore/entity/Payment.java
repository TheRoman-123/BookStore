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
public class Payment implements Serializable {
    @Serial
    private static final long serialVersionUID = 881254L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String type;

    private String cardName;

    private int cardNumber;

    private String expiryMonth;

    private String expiryYear;

    private int cvc;

    private String holderName;

    private boolean defaultPayment;

    @OneToOne
    @JsonIgnore
    private Order order;
}