package com.example.ebshop.service;

import com.example.ebshop.common.ResponseData;
import com.example.ebshop.dto.request.BookRequest;
import com.example.ebshop.model.Book;

public interface BookService {

    Book findBookByIsbn(String isbn);

    ResponseData insertBook(BookRequest bookRequest);

    boolean checkCreateBook(BookRequest bookRequest);

    int checkPublisherInBook(BookRequest bookRequest);

}
