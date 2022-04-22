package com.example.ebshop.mapper;

import com.example.ebshop.dto.request.BookRequest;
import com.example.ebshop.model.Book;

public interface BookMapper {
    Book findBookByIsbn(String isbn);

    void insertBook(Book book);

    void updateBook(Book book);


}
