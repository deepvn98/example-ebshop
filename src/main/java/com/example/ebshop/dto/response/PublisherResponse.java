package com.example.ebshop.dto.response;

import com.example.ebshop.model.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PublisherResponse {
    private String isbn;
    private String name;
    List<Book> bookList;
    private int quantityBook;

}
