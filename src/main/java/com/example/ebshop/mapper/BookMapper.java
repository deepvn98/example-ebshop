package com.example.ebshop.mapper;

import com.example.ebshop.dto.request.BookAuthorRequest;
import com.example.ebshop.dto.request.BookSearch;
import com.example.ebshop.dto.response.BookResponse;
import com.example.ebshop.model.Book;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BookMapper {

    BookResponse findBookByIsbn(String isbn);

    void insertBook(Book book);

    void updateBook(int id,Book book);

    void insertBookAuthor(BookAuthorRequest bookAuthorRequest);

    void deleteBookInBookAuthor(int id);

    void deleteBook(String isbn);

    List<Book> searchBook(@Param("bookSearch") BookSearch bookSearch);


}
