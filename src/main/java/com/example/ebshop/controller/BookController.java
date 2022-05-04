package com.example.ebshop.controller;

import com.example.ebshop.common.ResponseData;
import com.example.ebshop.dto.request.BookRequest;
import com.example.ebshop.dto.response.BookResponse;
import com.example.ebshop.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @PostMapping("/insert")
    public ResponseData createBook(@RequestBody BookRequest bookRequest){
        return bookService.insertBookInBookAuthor(bookRequest);
    }

    @GetMapping("/getall/{isbn}")
    public BookResponse getAllBook(@PathVariable String isbn){
        return bookService.findBookByIsbn(isbn);
    }

    @PutMapping("/delete/{isbn}")
    public ResponseData deleteBook(@PathVariable String isbn){
        return bookService.deleteBook(isbn);
    }

}
