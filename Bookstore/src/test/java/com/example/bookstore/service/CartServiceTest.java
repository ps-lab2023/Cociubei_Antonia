package com.example.bookstore.service;


import com.example.bookstore.dto.AddCartDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.User;
import com.example.bookstore.repositories.CartRepository;
import com.example.bookstore.repositories.UserRepository;
import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CartService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.mockito.Mockito.when;

public class CartServiceTest {

    @Mock
    private BookService produsService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @Test
    public void testAddToCart() {
        // Arrange
        AddCartDto addCartDto = new AddCartDto();
        addCartDto.setUserId(1L);
        addCartDto.setBookId(2L);

        User user = new User();
        user.setId(1L);
        Cart cart = new Cart();
        cart.setTotalPrice(0);
        user.setCart(cart);

        Book book = new Book();
        book.setId(2L);
        book.setPrice(10.99);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(produsService.findById(2L)).thenReturn(book);


        Cart result = cartService.addToCart(addCartDto);

        // Assert
        Assertions.assertEquals(result, cart);
        Assertions.assertEquals(result.getTotalPrice(), 10.0);
        Assertions.assertEquals(result.getBooks().size(), 1);


    }
}


