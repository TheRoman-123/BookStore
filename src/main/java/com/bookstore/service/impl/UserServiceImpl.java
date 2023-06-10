package com.bookstore.service.impl;

import com.bookstore.entity.*;
import com.bookstore.repository.*;
import com.bookstore.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final UserShippingRepository userShippingRepository;

    private final UserBillingRepository userBillingRepository;

    private final UserPaymentRepository userPaymentRepository;

    @Transactional
    public User createUser(User user) {
        User localUser = userRepository.findByUsername(user.getUsername());
        if (localUser != null) {
            LOG.info("User with username {} already exist. Nothing will be done", user.getUsername());
        } else {
            user.setUserShippingList(new ArrayList<>());
            user.setUserPaymentList(new ArrayList<>());
            ShoppingCart shoppingCart = new ShoppingCart();
            shoppingCart.setUser(user);
            user.setShoppingCart(shoppingCart);
            localUser = userRepository.save(user);
        }

        return localUser;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void updateShipping(UserShipping userShipping, User user) {
        userShipping.setUser(user);
        userShipping.setDefaultShipping(true);
        user.getUserShippingList().add(userShipping);
        userRepository.save(user);
    }

    @Override
    public void setDefaultShipping(int shippingId, User user) {
        List<UserShipping> userShippingList = userShippingRepository.findAll();

        for (UserShipping userShipping : userShippingList) {
            if (userShipping.getId() == shippingId) {
                userShipping.setDefaultShipping(true);
                userShippingRepository.save(userShipping);
            } else {
                userShipping.setDefaultShipping(false);
                userShippingRepository.save(userShipping);
            }
        }
    }


    @Override
    public void updateUserPayment(UserPayment userPayment, UserBilling userBilling, User user) {
        userPayment.setUser(user);
        userPayment.setUserBilling(userBilling);
        userPayment.setDefaultPayment(true);
        System.out.println("UserBilling: " + userBilling);
        System.out.println(userPayment);
        userBilling.setUserPayment(userPayment);
        user.getUserPaymentList().add(userPayment);
        userPaymentRepository.save(userPayment);
        userBillingRepository.save(userBilling);
        userRepository.save(user);

    }

    @Override
    public void setDefaultUserPayment(int userPaymentId, User user) {
        List<UserPayment> userPaymentList = userPaymentRepository.findAll();

        for (UserPayment userPayment : userPaymentList) {
            if (userPayment.getId() == userPaymentId) {
                userPayment.setDefaultPayment(true);
                userPaymentRepository.save(userPayment);
            } else {
                userPayment.setDefaultPayment(false);
                userPaymentRepository.save(userPayment);
            }
        }
    }


}
