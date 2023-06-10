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
public class UserBilling implements Serializable {
    @Serial
    private static final long serialVersionUID = 3644889656L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String userBillingName;

    private String userBillingStreet;

    private String userBillingCity;

    @OneToOne
    @JsonIgnore
    private UserPayment userPayment;
}
