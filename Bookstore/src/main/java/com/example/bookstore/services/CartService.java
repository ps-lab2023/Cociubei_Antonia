package com.example.bookstore.services;

import com.example.bookstore.dto.AddCartDto;
import com.example.bookstore.model.Cart;
import org.springframework.stereotype.Component;

@Component
public interface CartService {



    Cart addToCart(AddCartDto addCartDto);
    Cart save();
    Cart emptyCart(Long userId);

    int total(Long clientId);

}