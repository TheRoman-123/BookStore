package com.bookstore.repository;

import com.bookstore.entity.UserBilling;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserBillingRepository extends JpaRepository<UserBilling, Integer> {
}