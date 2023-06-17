package com.bookstore.service.impl;

import com.bookstore.entity.OrderItem;
import com.bookstore.repository.ShoppingCartRepository;
import com.bookstore.service.CartItemService;
import com.bookstore.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ShoppingCartServiceimpl implements ShoppingCartService {

    @Autowired
    private CartItemService cartItemService;
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        BigDecimal cartTotal = new BigDecimal(0);
        List<OrderItem> orderItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getBook().getInStockNumber() > 0) {
                cartItemService.updateCartItem(orderItem);
                cartTotal = cartTotal.add(orderItem.getSubTotal());
            }
        }
        shoppingCart.setTotal(cartTotal);
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;

    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        List<OrderItem> orderItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (OrderItem orderItem : orderItemList) {
            cartItemService.removeCartItem(orderItem);
        }

        shoppingCart.setTotal(new BigDecimal(0));
        shoppingCartRepository.save(shoppingCart);
    }
}
