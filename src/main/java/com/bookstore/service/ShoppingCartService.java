package com.bookstore.service;

public interface ShoppingCartService {

    ShoppingCart update(ShoppingCart shoppingCart);

    void clear(ShoppingCart shoppingCart);
}
