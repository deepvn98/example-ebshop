package com.example.ebshop.mapper;

import com.example.ebshop.model.Author;
import com.example.ebshop.model.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AuthorMapper {
    Author findAuthorByIsbn(String isbn);

    List<Author> findAllAuthor();

    Author findById(Long id);

    void insertAuthor(Author author);

    void updateAuthor(Author author, String isbn);

    void deleteAuthor(String isbn);

    List<Book> findListBookInAuthorByIsbn(String isbn);

    List<Book> topBookList(String isbn);
}
