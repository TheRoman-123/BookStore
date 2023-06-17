package com.bookstore.controller;

import com.bookstore.entity.OrderItem;
import com.bookstore.entity.Order;
import com.bookstore.entity.User;
import com.bookstore.service.CartItemService;
import com.bookstore.service.OrderService;
import com.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final UserService userService;

    private final CartItemService cartItemService;

    private final OrderService orderService;

    @GetMapping("/getOrderList")
    public List<Order> getOrderList(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<Order> orderList = user.getOrderList();

        return orderList;
    }

    @GetMapping("/getCartItemList")
    public List<OrderItem> getCartItemList(
            @RequestBody String orderId,
            Principal principal
    ) {
        Order order = orderService.findOne(Integer.parseInt(orderId));
        List<OrderItem> orderItemList = cartItemService.findByOrder(order);
        return orderItemList;
    }
}
