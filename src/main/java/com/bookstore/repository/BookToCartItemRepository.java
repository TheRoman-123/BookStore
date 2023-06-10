package com.bookstore.repository;

import com.bookstore.entity.BookToCartItem;
import com.bookstore.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookToCartItemRepository extends JpaRepository<BookToCartItem, Integer> {

    void deleteByCartItem(CartItem cartItem);
}
