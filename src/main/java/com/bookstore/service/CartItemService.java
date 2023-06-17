package com.bookstore.service;

import com.bookstore.entity.*;

import java.util.List;

public interface CartItemService {

    OrderItem addBookToCartItem(Book book, User user, int quantity);

    List<OrderItem> findByShoppingCart(ShoppingCart shoppingCart);

    List<OrderItem> findByOrder(Order order);

    OrderItem updateCartItem(OrderItem orderItem);

    void removeCartItem(OrderItem orderItem);

    OrderItem findById(int id);

    OrderItem save(OrderItem orderItem);
}
