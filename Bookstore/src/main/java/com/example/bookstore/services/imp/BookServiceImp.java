package com.example.bookstore.services.imp;

import com.example.bookstore.constraints.Category;
import com.example.bookstore.constraints.Rol;
import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.Book;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.repositories.UserRepository;
import com.example.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImp implements BookService {

    private BookRepository bookRepository;
    private UserRepository userRepository;


    public BookServiceImp(BookRepository bookRepository){
        this.bookRepository=bookRepository;
    }

    @Autowired
    public BookServiceImp(BookRepository bookRepository, UserRepository userRepository){
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }
    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id).get();
    }


    @Override
    public List<Book> findAll() {
        List<Book> booksList=(List<Book>)bookRepository.findByQuantityGreaterThan(0);
        return booksList;
    }

    @Override
    public Book save(BookDto book) {

        return bookRepository.save(Book.builder()
                .author(book.getAuthor())
                        .price(book.getPrice())
                        .category(book.getCategory())
                        .quantity(book.getQuantity())
                        .publisher(book.getPublisher())
                        .title(book.getTitle())
                        .author(book.getAuthor())
                .build());
    }

    @Override
    public Book findByTitle(String title) {
        return bookRepository.findBookByTitle(title);
    }

    @Override
    public List<Book> findByCategory(Category category){
        return bookRepository.findByCategory(category);
    }

    @Override
    public List<Book> findByPriceBetween(int low, int high) {
        return bookRepository.findByPriceIsBetween(low,high);
    }



  @Override
    public Book updateBook(Long id, double price, Rol role) {

      if(role == Rol.ADMIN) {
        Book updateBook= bookRepository.findById(id).get();
        updateBook.setPrice(price);
        bookRepository.save(updateBook);
        return updateBook;}

      else {
          throw new RuntimeException("Only admins can update books");
      }
    }

    @Override
    public Book deleteBook(Long id, Rol role) {
        if(role == Rol.ADMIN) {
        Book book = bookRepository.findById(id).get();
        bookRepository.delete(book);
        return book;}
        else {
                throw new RuntimeException("Only admins can delete books");
            }

    }

}
