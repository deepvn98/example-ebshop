package com.example.ebshop.service.impl;

import com.example.ebshop.common.ResponseData;
import com.example.ebshop.common.exception.HandleException;
import com.example.ebshop.common.exception.PublisherException;
import com.example.ebshop.dto.request.PublisherRequest;
import com.example.ebshop.mapper.PublisherMapper;
import com.example.ebshop.model.Publisher;
import com.example.ebshop.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PublisherImpl implements PublisherService {
    @Autowired
    private PublisherMapper publisherMapper;


    @Override
    public List<Publisher> findAllPublisher() {
        return publisherMapper.findAllPublisher();
    }

    @Override
    public Publisher findPublisherById(Long id) {
        return publisherMapper.findById(id);
    }

    @Override
    public ResponseData insertPublisher(PublisherRequest publisherRequest) {
        ResponseData responseData = new ResponseData();
        Publisher newPublisher = new Publisher();
        try {
            PublisherException.checkException(publisherRequest);
        } catch (HandleException e) {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage(e.getMessage());
            responseData.setObject(null);
            responseData.setCode(e.getCode());
            e.printStackTrace();
        }
        Publisher publisher = findPublisherByIsbn(publisherRequest.getIsbn());
        if (publisher== null){
            newPublisher.setIsbn(publisherRequest.getIsbn());
            newPublisher.setName(publisherRequest.getName());
            publisherMapper.insertPublisher(newPublisher);
            responseData.setHttpStatus(HttpStatus.CREATED);
            responseData.setMessage("SUCCESS");
            responseData.setObject(newPublisher);
            responseData.setCode("200");
        }
        else {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage("Publisher have in the database!");
            responseData.setObject(null);
            responseData.setCode("400");
        }
        return responseData;
    }

    @Override
    public void updatePublisher(PublisherRequest publisherRequest, int id) {

    }

    @Override
    public void deletePublisher(Long id) {

    }

    @Override
    public Publisher findPublisherByIsbn(String isbn) {
         Publisher publisher = publisherMapper.findPublisherByIsbn(isbn);
        return publisher;
    }

    @Override
    public void insert(String isbn, String name) {
        ResponseData responseData = new ResponseData();
        Publisher publisher = findPublisherByIsbn(isbn);
        if (publisher == null){
            publisherMapper.insertPublisher(isbn,name);
            responseData.setHttpStatus(HttpStatus.CREATED);
            responseData.setMessage("SUCCESS");
            responseData.setObject(new Publisher(isbn,name));
            responseData.setCode("200");
        }
        else {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage("Publisher have in the database!");
            responseData.setObject(null);
            responseData.setCode("400");
        }

    }

}

