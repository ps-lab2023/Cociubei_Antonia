package com.example.bookstore.services;

import com.example.bookstore.model.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    User findByEmail(String email);
    User register(String name, String password);
    User logIn(String name, String password);

    User findByNameAndPassword(String name, String password);
    User save (User user);
    List<User> findAll();

    User updateUserEmail(Long id, String email);

    User deleteUser(Long id);
}
