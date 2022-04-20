package com.example.ebshop.service;

import com.example.ebshop.common.ResponseData;
import com.example.ebshop.dto.request.PublisherRequest;
import com.example.ebshop.model.Publisher;

import java.util.List;

public interface PublisherService {
    List<Publisher> findAllPublisher();

    Publisher findPublisherById(Long id);

    ResponseData insertPublisher(PublisherRequest publisherRequest);

    ResponseData updatePublisher(PublisherRequest publisherRequest, String isbn);

    void deletePublisher(Long id);

    Publisher findPublisherByIsbn(String isbn);

    void insert(String isbn,String name);

}
