package com.example.ebshop.service.impl;

import com.example.ebshop.common.ResponseData;
import com.example.ebshop.common.exception.HandleException;
import com.example.ebshop.common.exception.PublisherException;
import com.example.ebshop.dto.request.PublisherRequest;
import com.example.ebshop.dto.response.PublisherResponse;
import com.example.ebshop.mapper.PublisherMapper;
import com.example.ebshop.model.Book;
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
        Publisher publisher = findPublisherByIsbn(publisherRequest.getIsbn());
        if (publisher == null) {
            newPublisher.setIsbn(publisherRequest.getIsbn());
            newPublisher.setName(publisherRequest.getName());
            publisherMapper.insertPublisher(newPublisher);
            responseData.setHttpStatus(HttpStatus.CREATED);
            responseData.setMessage("SUCCESS");
            responseData.setObject(newPublisher);
            responseData.setCode("200");
        } else {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage("Publisher have in the database!");
            responseData.setObject(null);
            responseData.setCode("400");
        }
        } catch (HandleException e) {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage(e.getMessage());
            responseData.setObject(null);
            responseData.setCode(e.getCode());
            e.printStackTrace();
        }
        return responseData;
    }

    @Override
    public ResponseData updatePublisher(PublisherRequest publisherRequest, String isbn) {
        ResponseData responseData = new ResponseData();
        try {
            PublisherException.checkException(publisherRequest);
        Publisher publisherInData = findPublisherByIsbn(isbn);
        if (publisherInData == null) {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage("Publisher dont have in the Database!");
            responseData.setObject("");
            responseData.setCode("404");
        } else {
            publisherInData.setIsbn(publisherRequest.getIsbn());
            publisherInData.setName(publisherRequest.getName());
            publisherMapper.updatePublisher(publisherInData, isbn);
            responseData.setHttpStatus(HttpStatus.OK);
            responseData.setMessage("SUCCESS");
            responseData.setObject(publisherInData);
            responseData.setCode("200");
        }
        } catch (HandleException e) {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage(e.getMessage());
            responseData.setObject(null);
            responseData.setCode(e.getCode());
            e.printStackTrace();
        }
        return responseData;
    }

    @Override
    public ResponseData deletePublisher(String isbn) {
        ResponseData responseData = new ResponseData();
        Publisher publisherInData = findPublisherByIsbn(isbn);
        if (publisherInData == null) {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage("Not Found Publisher in the Database!");
            responseData.setObject("");
            responseData.setCode("404");
        } else {
            List<Book> bookList = publisherMapper.findListBookInPublisherByIsbn(isbn);
            if (bookList.size() > 0) {
                responseData.setHttpStatus(HttpStatus.OK);
                responseData.setMessage("NOT DELETE PUBLISHER!");
                responseData.setCode("403");
            } else {
                publisherMapper.deletePublisher(isbn);
                responseData.setHttpStatus(HttpStatus.OK);
                responseData.setMessage("DELETE SUCCESS!");
                responseData.setObject(publisherInData);
                responseData.setCode("200");
            }
        }
        return responseData;
    }

    @Override
    public ResponseData getInformationPublisher(String isbn) {
        PublisherResponse publisherResponse = new PublisherResponse();
        ResponseData responseData = new ResponseData();
        Publisher publisherInData = findPublisherByIsbn(isbn);
        if (publisherInData == null) {
            responseData.setHttpStatus(HttpStatus.BAD_REQUEST);
            responseData.setMessage("Not Found Publisher in the Database!");
            responseData.setObject("");
            responseData.setCode("404");
        } else {
            List<Book> bookList = publisherMapper.findListBookInPublisherByIsbn(isbn);
            List<Book> list = publisherMapper.topBookList(isbn);
            publisherResponse.setIsbn(publisherInData.getIsbn());
            publisherResponse.setName(publisherInData.getName());
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
    public Publisher findPublisherByIsbn(String isbn) {
        return publisherMapper.findPublisherByIsbn(isbn);
    }


}

