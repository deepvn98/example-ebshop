package com.example.ebshop.dto.request;

import com.example.ebshop.model.Author;
import com.example.ebshop.model.Publisher;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BookRequest {
    private String isbn;
    private String name;
    private double price;
    private int quantity;
    private boolean status;
    private List<Author> authorList;
    private PublisherRequest publisher;
}
