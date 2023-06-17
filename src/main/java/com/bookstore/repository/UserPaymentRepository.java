package com.bookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPaymentRepository extends JpaRepository<UserPayment, Integer> {
}
