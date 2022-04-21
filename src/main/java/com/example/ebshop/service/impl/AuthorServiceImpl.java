package com.example.ebshop.service.impl;

import com.example.ebshop.common.ResponseData;
import com.example.ebshop.common.exception.AuthorException;
import com.example.ebshop.common.exception.HandleException;
import com.example.ebshop.common.exception.PublisherException;
import com.example.ebshop.dto.request.AuthorRequest;
import com.example.ebshop.dto.response.PublisherResponse;
import com.example.ebshop.mapper.AuthorMapper;
import com.example.ebshop.model.Author;
import com.example.ebshop.model.Book;
import com.example.ebshop.model.Publisher;
import com.example.ebshop.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorMapper authorMapper;

    @Override
    public List<Author> findAllAuthor() {
        return null;
    }

    @Override
    public Author findAuthorById(Long id) {
        return null;
    }

    @Override
    public ResponseData insertAuthor(AuthorRequest authorRequest) {
        ResponseData responseData = new ResponseData();
        Author author = new Author();
        try {
            AuthorException.checkException(authorRequest);
        } catch (HandleException e) {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage(e.getMessage());
            responseData.setObject(null);
            responseData.setCode(e.getCode());
            e.printStackTrace();
        }
        Author authorInData = authorMapper.findAuthorByIsbn(authorRequest.getIsbn());
        if (authorInData== null){
            author.setIsbn(authorRequest.getIsbn());
            author.setName(authorRequest.getName());
            authorMapper.insertAuthor(author);
            responseData.setHttpStatus(HttpStatus.CREATED);
            responseData.setMessage("SUCCESS");
            responseData.setObject(author);
            responseData.setCode("200");
        } else {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage("Author have in the database!");
            responseData.setObject(null);
            responseData.setCode("400");
        }
        return responseData;
    }

    @Override
    public ResponseData updateAuthor(AuthorRequest authorRequest, String isbn) {
        ResponseData responseData = new ResponseData();
        try {
            AuthorException.checkException(authorRequest);
        } catch (HandleException e) {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage(e.getMessage());
            responseData.setObject(null);
            responseData.setCode(e.getCode());
            e.printStackTrace();
        }
        Author authorInData = findAuthorByIsbn(isbn);
        if (authorInData == null) {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage("Author don't have in the Database!");
            responseData.setObject("");
            responseData.setCode("404");
        } else {
            authorInData.setIsbn(authorRequest.getIsbn());
            authorInData.setName(authorRequest.getName());
            authorMapper.updateAuthor(authorInData, isbn);
            responseData.setHttpStatus(HttpStatus.OK);
            responseData.setMessage("SUCCESS");
            responseData.setObject(authorInData);
            responseData.setCode("200");
        }
        return responseData;
    }


    @Override
    public ResponseData deleteAuthor(String isbn) {
        ResponseData responseData = new ResponseData();
        Author authorInData = findAuthorByIsbn(isbn);
        if (authorInData == null) {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage("Not Found Author in the Database!");
            responseData.setObject("");
            responseData.setCode("404");
        } else {
            List<Book> bookList = authorMapper.findListBookInAuthorByIsbn(isbn);
            if (bookList.size() > 0) {
                responseData.setHttpStatus(HttpStatus.OK);
                responseData.setMessage("NOT DELETE AUTHOR!");
                responseData.setCode("403");
            } else {
                authorMapper.deleteAuthor(isbn);
                responseData.setHttpStatus(HttpStatus.OK);
                responseData.setMessage("DELETE SUCCESS!");
                responseData.setObject(authorInData);
                responseData.setCode("200");
            }
        }
        return responseData;
    }

    @Override
    public ResponseData getInformationAuthor(String isbn) {
        PublisherResponse publisherResponse = new PublisherResponse();
        ResponseData responseData = new ResponseData();
        Author authorInData = findAuthorByIsbn(isbn);
        if (authorInData == null) {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage("Not Found Author in the Database!");
            responseData.setObject("");
            responseData.setCode("404");
        } else {
            List<Book> bookList = authorMapper.findListBookInAuthorByIsbn(isbn);
            List<Book> list = authorMapper.topBookList(isbn);
            publisherResponse.setIsbn(authorInData.getIsbn());
            publisherResponse.setName(authorInData.getName());
            publisherResponse.setBookList(list);
            publisherResponse.setQuantityBook(bookList.size());
            responseData.setHttpStatus(HttpStatus.OK);
            responseData.setMessage("SUCCESS!");
            responseData.setObject(publisherResponse);
            responseData.setCode("200");
        }

        return responseData;
    }

    @Override
    public Author findAuthorByIsbn(String isbn) {
        return authorMapper.findAuthorByIsbn(isbn);
    }
}
