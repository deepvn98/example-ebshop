package com.example.ebshop.service;

import com.example.ebshop.common.ResponseData;
import com.example.ebshop.dto.request.BookRequest;
import com.example.ebshop.dto.response.BookResponse;
import com.example.ebshop.model.Author;

import java.util.List;

public interface BookService {

    BookResponse findBookByIsbn(String isbn);

    ResponseData insertBookInBookAuthor(BookRequest bookRequest);

    int getPublisherIDInBook(BookRequest bookRequest);

    List<Author> getAuthorListInBook(BookRequest bookRequest);

    List<Integer> getIdAuthor(BookRequest bookRequest);

    ResponseData createBook(BookRequest bookRequest);

    ResponseData updateBook(BookResponse bookInData, BookRequest bookRequest);

    ResponseData deleteBook(int id);

}
