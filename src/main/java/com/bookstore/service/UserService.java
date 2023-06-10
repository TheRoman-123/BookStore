package com.bookstore.service;

import com.bookstore.entity.User;
import com.bookstore.entity.UserBilling;
import com.bookstore.entity.UserPayment;
import com.bookstore.entity.UserShipping;

public interface UserService {

    User createUser(User user);

    User findByUsername(String username);

    User findByEmail(String email);

    User findById(int id);

    User save(User user);

    void updateShipping(UserShipping userShipping, User user);

    void setDefaultShipping(int shippingId, User user);

    void updateUserPayment(UserPayment userPayment, UserBilling userBilling, User user);

    void setDefaultUserPayment(int userPaymentId, User user);

}
