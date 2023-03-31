package com.example.bookstore.service;

import com.example.bookstore.constraints.Rol;
import com.example.bookstore.model.Book;
import static org.mockito.Mockito.*;
import com.example.bookstore.repositories.BookRepository;
import com.example.bookstore.services.imp.BookServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.initMocks;




public class BookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImp bookService;
    private static final double newPrice=30.0;
    private Book book;

    @BeforeEach
    void init(){
        initMocks(this);
        book=new Book();
        book.setId(1L);
        book.setPrice(9.99);

        //  mock behavior
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

    }

    @Test
    public void testUpdateBookAsAdmin() {

        Book result = bookService.updateBook(book.getId(), newPrice, Rol.ADMIN);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(book, result);
        Assertions.assertEquals(newPrice, result.getPrice());

    }

    @Test
    public void testUpdateBookAsClient() {

        assertThrows(RuntimeException.class, () -> {
            bookService.updateBook(1L, 19.99, Rol.CLIENT);
        });
    }


}

