package com.bookstore.service.impl;

import com.bookstore.entity.UserPayment;
import com.bookstore.repository.UserPaymentRepository;
import com.bookstore.service.UserPaymentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPaymentServiceImpl implements UserPaymentService {

    private final UserPaymentRepository userPaymentRepository;

    @Override
    public UserPayment findById(int id) {
        return userPaymentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void remove(int id) {
        userPaymentRepository.deleteById(id);
    }
}
