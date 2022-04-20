package com.example.ebshop.mapper;

import com.example.ebshop.model.Publisher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PublisherMapper {
    Publisher findPublisherByIsbn(String isbn);

    List<Publisher> findAll();

    Publisher findById(Long id);

    void insert(Publisher publisher);

    void update(Publisher publisher, int id);

    void delete(Publisher id);

    void insertPublisher(String isbn, String name);

}
