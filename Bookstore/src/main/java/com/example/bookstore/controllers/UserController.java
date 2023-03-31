package com.example.bookstore.controllers;

import com.example.bookstore.dto.AddCartDto;
import com.example.bookstore.dto.RegisterDto;
import com.example.bookstore.dto.UpdateEmailDto;
import com.example.bookstore.services.CartService;
import com.example.bookstore.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService ;
    private final CartService cartService;


    public UserController(UserService userService, CartService cartService) {
        this.userService = userService;
        this.cartService = cartService;
    }
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterDto registerDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.register(registerDto.getName(),registerDto.getPassword()));
    }

    @PostMapping("/updateEmail")
    public ResponseEntity updateEmail (@RequestBody UpdateEmailDto updateEmailDto){
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUserEmail(updateEmailDto.getId(),updateEmailDto.getEmail()));
    }

    @GetMapping("/listAll")
    public ResponseEntity listAllUsers (){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
    }

   /* @PostMapping("/addToCart")
    public ResponseEntity addToCart (@RequestBody AddCartDto addCartDto){
        return ResponseEntity.status(HttpStatus.OK).body(cartService.addToCart(addCartDto));
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById (@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(userService.deleteUser(id));
    }




}
