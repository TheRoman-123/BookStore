package com.bookstore.controller;

import com.bookstore.entity.*;
import com.bookstore.service.CartItemService;
import com.bookstore.service.OrderService;
import com.bookstore.service.ShoppingCartService;
import com.bookstore.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/checkout")
@RequiredArgsConstructor
public class CheckoutController {

    private final UserService userService;

    private final OrderService orderService;

    private final CartItemService cartItemService;

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/checkout")
    public Order checkout(
            @RequestBody HashMap<String, Object> mapper,
            Principal principal
    ) {

        ObjectMapper objectMapper = new ObjectMapper();

        ShippingAddress shippingAddress = objectMapper.convertValue(mapper.get("shippingAddress"), ShippingAddress.class);
        BillingAddress billingAddress = objectMapper.convertValue(mapper.get("billingAddress"), BillingAddress.class);
        Payment payment = objectMapper.convertValue(mapper.get("payment"), Payment.class);

        ShoppingCart shoppingCart = userService.findByUsername(principal.getName()).getShoppingCart();
        List<OrderItem> orderItemList = cartItemService.findByShoppingCart(shoppingCart);
        User user = userService.findByUsername(principal.getName());
        Order order = orderService.createOrder(shoppingCart, shippingAddress, billingAddress, payment, user);

        shoppingCartService.clear(shoppingCart);

        return order;
    }
}
