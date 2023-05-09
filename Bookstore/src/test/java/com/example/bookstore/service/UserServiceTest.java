package com.example.bookstore.service;


import com.example.bookstore.constraints.Rol;
import com.example.bookstore.model.User;
import com.example.bookstore.repositories.UserRepository;
import com.example.bookstore.services.CartService;
import com.example.bookstore.services.imp.UserServiceImp;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


public class UserServiceTest {
    private static final String NAME = "John";
    private static final String NAME_NOT = "Kate";

    private static final String PASSWORD = "1234";
    private static final String PASSWORD_NOT = "blabalabala";
    private UserServiceImp userServiceImp;

    @Mock
    private UserRepository userRepository;
    private CartService cartService;


    private User user;

    @BeforeEach
    void init() {
        initMocks(this);
        user = new User();
        user.setId(3L);
        user.setName("John");
        user.setPassword("1234");
        user.setRol(Rol.CLIENT);

        when(userRepository.findByNameAndPassword(NAME,PASSWORD)).thenReturn(user);
    }

    @Test
    void givenExistingUser_whenFindByNameAndPassword_thenFindOne() {


        userServiceImp = new UserServiceImp(userRepository, cartService);

        User user = userServiceImp.findByNameAndPassword(NAME,PASSWORD);

        Assertions.assertNotNull(user);
        Assertions.assertEquals(NAME,user.getName());
        Assertions.assertEquals(PASSWORD,user.getPassword());
    }


    @Test
    void givenNonExistingUser_whenFindByNameAndPassword_thenThrowException() {
        when(userRepository.findByNameAndPassword(NAME_NOT,PASSWORD_NOT)).thenReturn(null);

        Exception exception = assertThrows(NullPointerException.class, () -> {
            userServiceImp.findByNameAndPassword(NAME_NOT,PASSWORD_NOT);
        });

    }
}
