package com.bookstore.service;

public interface UserPaymentService {

    UserPayment findById(int id);

    void remove(int id);
}
