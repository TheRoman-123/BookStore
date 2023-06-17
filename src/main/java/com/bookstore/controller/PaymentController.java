package com.bookstore.controller;

import com.bookstore.entity.User;
import com.bookstore.service.UserPaymentService;
import com.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final UserPaymentService userPaymentService;

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Void> addNewUserPayment(@RequestBody UserPayment userPayment, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        UserBilling userBilling = userPayment.getUserBilling();
        userService.updateUserPayment(userPayment, userBilling, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getUserPaymentList")
    public List<UserPayment> getUserPaymentList(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<UserPayment> userPaymentList = user.getUserPaymentList();
        return userPaymentList;
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> removeUserPayment(
            @RequestBody String id,
            Principal principal
    ) {
        userPaymentService.remove(Integer.parseInt(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/setDefault")
    public ResponseEntity<Void> setDefaultPayment(
            @RequestBody String userPaymentId,
            Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());
        userService.setDefaultUserPayment(Integer.parseInt(userPaymentId), user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
