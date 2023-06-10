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
public class UserShipping implements Serializable {

    @Serial
    private static final long serialVersionUID = 443655L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String receiverName;

    private String street;

    private String city;

    private Boolean defaultShipping;

    @ManyToOne
    @JsonIgnore
    private User user;
}