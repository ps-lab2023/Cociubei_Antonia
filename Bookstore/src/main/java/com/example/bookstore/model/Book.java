package com.example.bookstore.model;

import com.example.bookstore.constraints.Category;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String author;
    private String publisher;
    private double price;
    private int quantity;
    private Category category;
}
