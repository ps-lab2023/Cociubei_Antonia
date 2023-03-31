package com.example.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddCartDto {


    private Long userId;
    private Long bookId;


    public AddCartDto() {

    }
}
