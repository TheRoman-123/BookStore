package com.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_order")
@Getter
@Setter
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 5442982158L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    
    private String orderDate;
    
    private String orderStatus;
    
    private int orderTotal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<CartItem> cartItemList;

    @OneToOne(cascade = CascadeType.ALL)
    private BillingAddress billingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private ShippingAddress shippingAddress;

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;

    @ManyToOne
    @JsonIgnore
    private User user;
}
