package com.example.bookstore.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class Cart {
    @Id
    @GeneratedValue
    private Long id;
    private double totalPrice;
    private int totalBooks;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> books;
}
