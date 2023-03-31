package com.example.bookstore.services.imp;

import com.example.bookstore.constraints.Rol;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.User;
import com.example.bookstore.repositories.UserRepository;
import com.example.bookstore.services.CartService;
import com.example.bookstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private final UserRepository userRepository;
    private final CartService cartService;


    public UserServiceImp(UserRepository userRepository, CartService cartService) {

        this.userRepository = userRepository;

        this.cartService = cartService;
    }


    @Override
    public User findByNameAndPassword(String name, String password) {
        return userRepository.findByNameAndPassword(name, password);
    }


    public User findByEmail(String email){

        return userRepository.findByEmail(email);
    }

    @Override
    public User register(String name, String password) {
        Cart cart=cartService.save();
        User user= User.builder()
                .name(name)
                .password(password)
                .cart(cart)
                .rol(Rol.CLIENT)
                .build();
        return userRepository.save(user);

    }

    @Override
    public User logIn(String name, String password) {

        return userRepository.findByNameAndPassword(name, password);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User updateUserEmail(Long id, String email) {
        User updateUser = userRepository.findById(id).get();
        updateUser.setEmail(email);
        userRepository.save(updateUser);
        return updateUser;
    }

    @Override
    public User deleteUser(Long id) {
        User userToDelete = userRepository.findById(id).get();
        userRepository.delete(userToDelete);
        return userToDelete;
    }

}
