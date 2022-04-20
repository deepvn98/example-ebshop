package com.example.ebshop.mapper;

import com.example.ebshop.dto.response.PublisherResponse;
import com.example.ebshop.model.Book;
import com.example.ebshop.model.Publisher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublisherMapper {
    Publisher findPublisherByIsbn(String isbn);

    List<Publisher> findAllPublisher();

    Publisher findById(Long id);

    void insertPublisher(Publisher publisher);

    void updatePublisher(Publisher publisher, String isbn);

    void deletePublisher(String isbn);

    List<Book> findListBookInPublisherByIsbn(String isbn);

    PublisherResponse getInformationPublisher(String isbn);
}
