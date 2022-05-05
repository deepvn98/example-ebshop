package com.example.ebshop.service.impl;

import com.example.ebshop.common.ResponseData;
import com.example.ebshop.dto.request.AuthorRequest;
import com.example.ebshop.dto.request.BookAuthorRequest;
import com.example.ebshop.dto.request.BookRequest;
import com.example.ebshop.dto.request.BookSearch;
import com.example.ebshop.dto.response.BookResponse;
import com.example.ebshop.mapper.BookMapper;
import com.example.ebshop.model.Author;
import com.example.ebshop.model.Book;
import com.example.ebshop.model.Publisher;
import com.example.ebshop.service.AuthorService;
import com.example.ebshop.service.BookService;
import com.example.ebshop.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private AuthorService authorService;

    @Override
    public BookResponse findBookByIsbn(String isbn) {
        return bookMapper.findBookByIsbn(isbn);
    }


    @Override
    public ResponseData insertBookInBookAuthor(BookRequest bookRequest) {
        ResponseData responseData;
        BookAuthorRequest bookAuthorRequest;
        BookResponse bookInData = findBookByIsbn(bookRequest.getIsbn());
        int bookId;
        if (bookInData == null) {
            List<Integer> idAuthorList = getIdAuthor(bookRequest);
            responseData = createBook(bookRequest);
            BookResponse book = bookMapper.findBookByIsbn(bookRequest.getIsbn());
            bookId = book.getId();
            for (int i = 0; i < idAuthorList.size(); i++) {
                bookAuthorRequest = new BookAuthorRequest(bookId, idAuthorList.get(i));
                bookMapper.insertBookAuthor(bookAuthorRequest);
            }
        } else {
            responseData = updateBook(bookInData, bookRequest);
            List<Integer> idAuthorList = getIdAuthor(bookRequest);
            bookId = bookInData.getId();
            bookMapper.deleteBookInBookAuthor(bookId);
            for (int i = 0; i < idAuthorList.size(); i++) {
                bookAuthorRequest = new BookAuthorRequest(bookId, idAuthorList.get(i));
                bookMapper.insertBookAuthor(bookAuthorRequest);
            }
        }
        return responseData;
    }

    @Override
    public ResponseData createBook(BookRequest bookRequest) {
        ResponseData responseData = new ResponseData();
        int idPublisher = getPublisherIDInBook(bookRequest);
        Book book = new Book();
        book.setIsbn(bookRequest.getIsbn());
        book.setName(bookRequest.getName());
        book.setPrice(bookRequest.getPrice());
        book.setQuantity(bookRequest.getQuantity());
        book.setStatus(false);
        book.setPublisher(idPublisher);
        bookMapper.insertBook(book);
        responseData.setObject(book);
        responseData.setMessage("Insert Success!");
        responseData.setCode("200");
        responseData.setHttpStatus(HttpStatus.ACCEPTED);
        return responseData;
    }

    @Override
    public ResponseData updateBook(BookResponse bookInData, BookRequest bookRequest) {
        ResponseData responseData = new ResponseData();
        Book book = new Book();
        int idBook = bookInData.getId();
        int quantityBook = bookInData.getQuantity() + bookRequest.getQuantity();
        int idPublisher = getPublisherIDInBook(bookRequest);
        book.setIsbn(bookRequest.getIsbn());
        book.setName(bookRequest.getName());
        book.setPrice(bookRequest.getPrice());
        book.setQuantity(quantityBook);
        book.setStatus(false);
        book.setPublisher(idPublisher);
        bookMapper.updateBook(idBook,book);
        responseData.setObject(book);
        responseData.setMessage("update Success!");
        responseData.setCode("200");
        responseData.setHttpStatus(HttpStatus.ACCEPTED);
        return responseData;
    }

    @Override
    public ResponseData deleteBook(String isbn) {
        ResponseData responseData = new ResponseData();
        BookResponse book = findBookByIsbn(isbn);
        if (book == null){
            responseData.setObject("");
            responseData.setMessage("not fount book in the database!");
            responseData.setCode("000");
            responseData.setHttpStatus(HttpStatus.NO_CONTENT);
        }else {
            bookMapper.deleteBook(isbn);
            int idBook = book.getId();
            book.setStatus(true);
            bookMapper.deleteBookInBookAuthor(idBook);
            responseData.setObject(book);
            responseData.setMessage("Delete Success!");
            responseData.setCode("200");
            responseData.setHttpStatus(HttpStatus.OK);
        }
        return responseData;
    }

    @Override
    public ResponseData searchBook(BookSearch bookSearch) {
        ResponseData responseData = new ResponseData();
        List<Book> listBook = bookMapper.searchBook(bookSearch);
        responseData.setObject(listBook);
        responseData.setCode("200");
        responseData.setMessage("ok");
        responseData.setHttpStatus(HttpStatus.OK);
        return responseData;
    }

    @Override
    public int getPublisherIDInBook(BookRequest bookRequest) {
        int idPublisher = 0;
        Publisher publisher = publisherService.findPublisherByIsbn(bookRequest.getIsbn());
        if (publisher == null) {
            publisherService.insertPublisher(bookRequest.getPublisher());
            Publisher newPublisher = publisherService.findPublisherByIsbn(bookRequest.getPublisher().getIsbn());
            idPublisher = newPublisher.getId();
        } else {
            idPublisher = publisher.getId();
        }
        return idPublisher;
    }

    @Override
    public List<Author> getAuthorListInBook(BookRequest bookRequest) {
        List<Author> authorList = bookRequest.getAuthorList();
        AuthorRequest authorRequest = new AuthorRequest();
        String isbn;
        for (Author author : authorList) {
            isbn = author.getIsbn();
            Author authorInData = authorService.findAuthorByIsbn(isbn);
            if (authorInData == null) {
                authorRequest.setIsbn(author.getIsbn());
                authorRequest.setName(author.getName());
                authorService.insertAuthor(authorRequest);
            }
        }
        return authorList;
    }

    @Override
    public List<Integer> getIdAuthor(BookRequest bookRequest) {
        List<Author> authorList = getAuthorListInBook(bookRequest);
        List<Integer> idAuthorList = new ArrayList<>();
        int id = 0;
        for (int i = 0; i < authorList.size(); i++) {
            Author authorInData = authorService.findAuthorByIsbn(authorList.get(i).getIsbn());
            id = authorInData.getId();
            idAuthorList.add(id);
        }
        return idAuthorList;
    }
}
