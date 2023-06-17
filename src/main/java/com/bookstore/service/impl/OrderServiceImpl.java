package com.bookstore.service.impl;

import com.bookstore.entity.*;
import com.bookstore.repository.BillingAddressRepository;
import com.bookstore.repository.OrderRepository;
import com.bookstore.repository.PaymentRepository;
import com.bookstore.repository.ShippingAddressRepository;
import com.bookstore.service.BookService;
import com.bookstore.service.CartItemService;
import com.bookstore.service.OrderService;
import com.bookstore.utility.MailConstructor;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ShippingAddressRepository shippingAddressRepository;

    private final BillingAddressRepository billingAddressRepository;

    private final PaymentRepository paymentRepository;

    private final CartItemService cartItemService;

    private final BookService bookService;

    private final MailConstructor mailConstructor;


    @Override
    public synchronized Order createOrder(ShoppingCart shoppingCart, ShippingAddress shippingAddress, BillingAddress billingAddress, Payment payment, User user) {

        Order order = new Order();
        order.setShippingAddress(shippingAddress);
        order.setBillingAddress(billingAddress);
        order.setPayment(payment);
        order.setOrderStatus("created");

        List<OrderItem> orderItemList = cartItemService.findByShoppingCart(shoppingCart);

        for (OrderItem orderItem : orderItemList) {
            Book book = orderItem.getBook();
            orderItem.setOrder(order);
            book.setInStockNumber(book.getInStockNumber() - orderItem.getQuantity());
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        order.setOrderItemList(orderItemList);
        order.setOrderDate(timeFormatter.format(localDateTime));
        order.setOrderTotal(shoppingCart.getTotal().intValue());
        shippingAddress.setOrder(order);
        billingAddress.setOrder(order);
        payment.setOrder(order);
        order.setUser(user);

        order = orderRepository.save(order);

        return order;
    }

    @Override
    public Order findOne(int id) {
        return orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }


}
