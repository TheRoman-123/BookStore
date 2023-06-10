package com.bookstore.controller;

import com.bookstore.entity.User;
import com.bookstore.entity.UserShipping;
import com.bookstore.service.UserService;
import com.bookstore.service.UserShippingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/shipping")
@RequiredArgsConstructor
public class ShippingController {

    private final UserShippingService userShippingService;

    private final UserService userService;

    @PostMapping("/add")
    public ResponseEntity<Void> addNewUserShipping(
            @RequestBody UserShipping userShipping,
            Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());
        userService.updateShipping(userShipping, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getUserShippingList")
    public List<UserShipping> getShippingList(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        List<UserShipping> userShippingList = user.getUserShippingList();
        return userShippingList;
    }

    //    @PostMapping("/remove")
    @DeleteMapping("/remove")
    public ResponseEntity<Void> removeShipping(
            @RequestBody String id,
            Principal principal
    ) {
        userShippingService.remove(Integer.parseInt(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/setDefault")
    public ResponseEntity<Void> setDefaultShipping(
            @RequestBody String id,
            Principal principal
    ) {
        User user = userService.findByUsername(principal.getName());
        userService.setDefaultShipping(Integer.parseInt(id), user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
