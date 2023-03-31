package com.example.bookstore.repositories;

import com.example.bookstore.constraints.Category;
import com.example.bookstore.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Book findBookByTitle(String title);

    List<Book> findByPriceIsBetween(int low, int high);

    List<Book> findByCategory(Category category);
    List<Book> findByQuantityGreaterThan(int val);





}
