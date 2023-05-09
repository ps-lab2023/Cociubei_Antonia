package com.example.bookstore.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class OrderHistory {
    @Id
    @GeneratedValue
    private Long id;
    private Long clientId;
    private int total;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Book> book;
}
