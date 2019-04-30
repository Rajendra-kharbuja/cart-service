package com.general.cart.controller;

import com.general.cart.model.entity.Cart;
import com.general.cart.model.entity.response.CartResponse;
import com.general.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/carts")
@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<CartResponse> createCart(@RequestHeader String tenant, @Valid @RequestBody Cart cart) {

        Long cartId = cartService.createCart(tenant, cart);
        return new ResponseEntity<>(new CartResponse(cartId), HttpStatus.CREATED);
    }

    @RequestMapping("/{cartId}")
    @GetMapping
    public ResponseEntity<Cart> getCart(@RequestHeader String tenant, @PathVariable Long cartId) {
        final Cart cart = cartService.findCartById(tenant, cartId);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}
