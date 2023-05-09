package com.example.bookstore.controllers;

import com.example.bookstore.dto.AddCartDto;
import com.example.bookstore.services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }
    @PostMapping("/addToCart")
    public ResponseEntity addToCart (@RequestBody AddCartDto addCartDto){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addToCart(addCartDto));
    }

    @DeleteMapping("/emptyCart/{id}")
    public ResponseEntity empltyCart(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.emptyCart(id));
    }
    @GetMapping("/totalPrice/{id}")
    public ResponseEntity total(@PathVariable Long id){
        return  ResponseEntity.status(HttpStatus.OK).body(cartService.total(id));
    }

}
