package com.example.ebshop.service.impl;

import com.example.ebshop.common.ResponseData;
import com.example.ebshop.dto.request.BookRequest;
import com.example.ebshop.mapper.BookMapper;
import com.example.ebshop.model.Author;
import com.example.ebshop.model.Book;
import com.example.ebshop.model.Publisher;
import com.example.ebshop.service.BookService;
import com.example.ebshop.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private PublisherService publisherService;

    @Override
    public Book findBookByIsbn(String isbn) {
        return bookMapper.findBookByIsbn(isbn);
    }



    @Override
    public ResponseData insertBook(BookRequest bookRequest) {
        ResponseData responseData = new ResponseData();
        // Check authorList xem co author moi thi tao moi author
        List<Author> authorList = bookRequest.getAuthorList();
       if (checkCreateBook(bookRequest)){

           //insert thong tin vao bang trung gian
       }else {
           //Xoa thong tin bang trung gian co isbn cua book
           //Sau do insert lai thong tin vao bang trung gian
       }
        return responseData;
    }

    @Override
    public boolean checkCreateBook(BookRequest bookRequest) {
        boolean checkCreateBook = false;
        Book book = new Book();
        Book bookInData = findBookByIsbn(bookRequest.getIsbn());
        int idPublisher = checkPublisherInBook(bookRequest);
        if (bookInData == null){
            book.setIsbn(bookRequest.getIsbn());
            book.setName(bookRequest.getName());
            book.setPrice(bookRequest.getPrice());
            book.setQuantity(bookRequest.getQuantity());
            book.setStatus(false);
            book.setPublisher(idPublisher);
            bookMapper.insertBook(bookInData);
            checkCreateBook = true;
        }else {
            int quantityBook = bookInData.getQuantity()+bookRequest.getQuantity();
            bookInData.setIsbn(bookRequest.getIsbn());
            bookInData.setName(bookRequest.getName());
            bookInData.setPrice(bookRequest.getPrice());
            bookInData.setQuantity(quantityBook);
            bookInData.setStatus(false);
            bookInData.setPublisher(idPublisher);
            bookMapper.updateBook(bookInData);
        }
        return checkCreateBook;
    }

    @Override
    public int checkPublisherInBook(BookRequest bookRequest) {
        int idPublisher = 0;
        Publisher publisher = publisherService.findPublisherByIsbn(bookRequest.getIsbn());
        if (publisher == null){
            publisherService.insertPublisher(bookRequest.getPublisher());
            Publisher newPublisher = publisherService.findPublisherByIsbn(bookRequest.getIsbn());
            idPublisher = newPublisher.getId();
        }else {
            idPublisher = publisher.getId();
        }
        return idPublisher;
    }
}
