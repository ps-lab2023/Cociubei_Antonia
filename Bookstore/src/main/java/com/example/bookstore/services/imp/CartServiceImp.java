package com.example.bookstore.services.imp;

import com.example.bookstore.dto.AddCartDto;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.OrderHistory;
import com.example.bookstore.model.User;
import com.example.bookstore.repositories.CartRepository;
import com.example.bookstore.repositories.UserRepository;

import com.example.bookstore.services.BookService;
import com.example.bookstore.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImp implements CartService {
   @Autowired
    private  final CartRepository cartRepository;
    private  final UserRepository userRepository;
    private  final BookService bookService;

    public CartServiceImp(CartRepository cartRepository, UserRepository userRepository, BookService bookService) {
        this.cartRepository = cartRepository;
        this.userRepository =userRepository;
        this.bookService=bookService;
    }

    @Override
    public Cart addToCart(AddCartDto addCartDto) {
        Long userId = addCartDto.getUserId();
        Book book = bookService.findById(addCartDto.getBookId());
        User user = userRepository.findById(userId).get();
        Cart cart =  user.getCart();
        cart.getBooks().add(book);
        double totalPrice = cart.getTotalPrice() + book.getPrice();
        cart.setTotalPrice(totalPrice);
        cart.setTotalBooks(cart.getTotalBooks()+1);
        return cartRepository.save(cart);
    }

    @Override
    public Cart save() {
        List books=new LinkedList<>();

        return cartRepository.save(Cart.builder()
                .totalPrice(0)
                .books(books)
                .totalBooks(0)
                .build());
    }


    @Override
    public Cart emptyCart(Long userId) {
        Cart cart = userRepository.findById(userId).get().getCart();
        cart.setTotalPrice(0);
        cart.setTotalBooks(0);
        cart.getBooks().clear();
        cartRepository.save(cart);
        return null;
    }


    @Override
    public int total(Long idPersoana) {

        return (int) userRepository.findById(idPersoana).get().getCart().getTotalPrice();
    }

}
