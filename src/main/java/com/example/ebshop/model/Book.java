package com.example.ebshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {
    private int id;
    private String isbn;
    private String name;
    private double price;
    private int quantity;
    private Boolean status;
    private int publisher;

}
