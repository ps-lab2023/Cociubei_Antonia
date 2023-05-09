package com.example.bookstore.repositories;

import com.example.bookstore.dto.AddCartDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends CrudRepository<Cart,Long> {

}
