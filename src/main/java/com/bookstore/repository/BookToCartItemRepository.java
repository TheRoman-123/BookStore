package com.bookstore.repository;

import com.bookstore.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookToCartItemRepository extends JpaRepository<BookToCartItem, Integer> {

    void deleteByCartItem(OrderItem orderItem);
}
