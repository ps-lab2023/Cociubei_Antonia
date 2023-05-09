package com.example.bookstore.controllers;

import com.example.bookstore.constraints.Rol;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.RegisterDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.services.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/save")
    public ResponseEntity saveBook(@RequestBody BookDto bookDto){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.save(bookDto));
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity deleteBook(@PathVariable Long id, @RequestHeader("Role") String role){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.deleteBook(id , Rol.valueOf(role)));
    }

    @GetMapping()
    public ResponseEntity findAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.findAll());
    }

    @PostMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable Long id, @RequestBody double price, @RequestHeader("Role") String role) {


            return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id,price, Rol.valueOf(role)));

    }


}
