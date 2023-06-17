package com.bookstore.service.impl;

import com.bookstore.repository.UserShippingRepository;
import com.bookstore.service.UserShippingService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserShippingServiceImpl implements UserShippingService {

    private final UserShippingRepository userShippingRepository;

    @Override
    public UserShipping findById(int id) {
        return userShippingRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public void remove(int id) {
        userShippingRepository.deleteById(id);
    }
}
