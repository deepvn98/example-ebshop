package com.example.ebshop.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Oder {
    private Long id;
    private String isbn;
    private Customer customerId;
}
