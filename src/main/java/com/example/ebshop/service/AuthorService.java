package com.example.ebshop.service;

import com.example.ebshop.common.ResponseData;
import com.example.ebshop.dto.request.AuthorRequest;
import com.example.ebshop.model.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAllAuthor();

    Author findAuthorById(Long id);

    ResponseData insertAuthor(AuthorRequest authorRequest);

    ResponseData updateAuthor(AuthorRequest authorRequest, String isbn);

    ResponseData deleteAuthor(String isbn);

    ResponseData getInformationAuthor(String isbn);

    Author findAuthorByIsbn(String isbn);
}
