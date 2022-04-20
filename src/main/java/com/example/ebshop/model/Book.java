package com.example.ebshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class Book {
    private Long id;
    private String isbn;
    private String name;
    private double price;
    private int quantity;
    private Boolean status;
    private List<Author>authorList;
    private Publisher publisher;

}
