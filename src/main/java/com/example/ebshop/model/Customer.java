package com.example.ebshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {
    private Long id;
    private String isbn;
    private String name;
    private String email;
    private String phone;
}
