package com.bookstore.service.impl;

import com.bookstore.entity.*;
import com.bookstore.repository.BookToCartItemRepository;
import com.bookstore.repository.CartItemRepository;
import com.bookstore.service.CartItemService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;

    private final BookToCartItemRepository bookToCartItemRepository;


    @Override
    public OrderItem addBookToCartItem(Book book, User user, int quantity) {
        List<OrderItem> orderItemList = findByShoppingCart(user.getShoppingCart());

        for (OrderItem orderItem : orderItemList) {
            if (book.getId() == orderItem.getBook().getId()) {
                orderItem.setQuantity(orderItem.getQuantity() + quantity);
                orderItem.setSubTotal(BigDecimal.valueOf(book.getOurPrice()).multiply(new BigDecimal(quantity)));
                cartItemRepository.save(orderItem);
                return orderItem;
            }
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(quantity);
        orderItem.setSubTotal(BigDecimal.valueOf(book.getOurPrice()).multiply(new BigDecimal(quantity)));
        orderItem.setBook(book);
        orderItem.setShoppingCart(user.getShoppingCart());

        orderItem = cartItemRepository.save(orderItem);

        BookToCartItem bookToCartItem = new BookToCartItem();
        bookToCartItem.setBook(book);
        bookToCartItem.setOrderItem(orderItem);
        bookToCartItemRepository.save(bookToCartItem);

        return orderItem;
    }

    @Override
    public List<OrderItem> findByShoppingCart(ShoppingCart shoppingCart) {
        return cartItemRepository.findByShoppingCart(shoppingCart);
    }

    @Override
    public List<OrderItem> findByOrder(Order order) {
        return cartItemRepository.findByOrder(order);
    }

    @Override
    public OrderItem updateCartItem(OrderItem orderItem) {
        BigDecimal bigDecimal = BigDecimal.valueOf(orderItem.getBook().getOurPrice()).multiply(new BigDecimal(orderItem.getQuantity()));
        bigDecimal = bigDecimal.setScale(2, RoundingMode.HALF_UP);
        orderItem.setSubTotal(bigDecimal);
        cartItemRepository.save(orderItem);
        return orderItem;
    }

    @Transactional
    @Override
    public void removeCartItem(OrderItem orderItem) {
        bookToCartItemRepository.deleteByCartItem(orderItem);
        cartItemRepository.delete(orderItem);
    }

    @Override
    public OrderItem findById(int id) {
        return cartItemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        return cartItemRepository.save(orderItem);
    }
}
