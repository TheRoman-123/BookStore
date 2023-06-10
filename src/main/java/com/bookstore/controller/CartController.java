package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.entity.CartItem;
import com.bookstore.entity.ShoppingCart;
import com.bookstore.entity.User;
import com.bookstore.service.BookService;
import com.bookstore.service.CartItemService;
import com.bookstore.service.ShoppingCartService;
import com.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final UserService userService;

    private final BookService bookService;

    private final CartItemService cartItemService;

    private final ShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ResponseEntity<Void> addItem(
            @RequestBody HashMap<String, String> mapper,
            Principal principal
    ) {
        String bookId = mapper.get("id");
        String quantity = mapper.get("quantity");
        User user = userService.findByUsername(principal.getName());
        Book book = bookService.findOne(Integer.parseInt(bookId));

        if (Integer.parseInt(quantity) > book.getInStockNumber()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        cartItemService.addBookToCartItem(book, user, Integer.parseInt(quantity));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/cartItemList")
    public List<CartItem> getCartItemList(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();
        List<CartItem> cartItemList = cartItemService.findByShoppingCart(shoppingCart);
        shoppingCartService.update(shoppingCart);
        return cartItemList;
    }

    @GetMapping("/shoppingCart")
    public ShoppingCart getShoppingCart(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        ShoppingCart shoppingCart = user.getShoppingCart();
        shoppingCartService.update(shoppingCart);
        return shoppingCart;
    }

    //    @RequestMapping(value = "/removeCartItem", method = RequestMethod.POST)
    @DeleteMapping("/removeCartItem")
    public ResponseEntity<Void> removeCartItem(@RequestBody String id) {
        CartItem cartItem = cartItemService.findById(Integer.parseInt(id));
        cartItemService.removeCartItem(cartItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    @RequestMapping(value = "/updateCartItem", method = RequestMethod.POST)
    @PutMapping("/updateCartItem")
    public ResponseEntity<Void> updateCartItem(@RequestBody HashMap<String, String> mapper) {
        String cartItemId = mapper.get("id");
        String quantity = mapper.get("quantity");
        CartItem cartItem = cartItemService.findById(Integer.parseInt(cartItemId));
        cartItem.setQuantity(Integer.parseInt(quantity));
        cartItemService.updateCartItem(cartItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
