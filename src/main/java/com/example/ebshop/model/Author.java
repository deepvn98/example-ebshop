package com.example.ebshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Author {
    private Long id;
    private String isbn;
    private String name;
}
