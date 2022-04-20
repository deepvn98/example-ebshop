package com.example.ebshop.controller;

import com.example.ebshop.common.ResponseData;
import com.example.ebshop.dto.request.PublisherRequest;
import com.example.ebshop.model.Publisher;
import com.example.ebshop.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    @GetMapping("/getall")
    public ResponseEntity<?> getAllPublisher() {
        List<Publisher> publishers = publisherService.findAllPublisher();
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseData insertPublisher(@RequestBody PublisherRequest publisherRequest){
        return publisherService.insertPublisher(publisherRequest);
    }

    @PostMapping("/getbyisbn")
    public ResponseEntity<?> getPublisherByIsbn(@RequestBody PublisherRequest publisherRequest) {
        Publisher publishers = publisherService.findPublisherByIsbn(publisherRequest.getIsbn());
        return new ResponseEntity<>(publishers, HttpStatus.OK);
    }


}
