package com.bookstore.repository;

import com.bookstore.entity.OrderItem;
import com.bookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByShoppingCart(ShoppingCart shoppingCart);

    List<OrderItem> findByOrder(Order order);
}
