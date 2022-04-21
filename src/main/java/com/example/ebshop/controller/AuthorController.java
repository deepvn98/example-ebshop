package com.example.ebshop.controller;

import com.example.ebshop.common.ResponseData;
import com.example.ebshop.dto.request.AuthorRequest;
import com.example.ebshop.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/insert")
    public ResponseData createAuthor(@RequestBody AuthorRequest authorRequest){
        return authorService.insertAuthor(authorRequest);
    }
    @PutMapping("/update/{isbn}")
    public ResponseData editAuthor(@RequestBody AuthorRequest authorRequest, @PathVariable String isbn){
        return authorService.updateAuthor(authorRequest,isbn);
    }

    @DeleteMapping("/delete/{isbn}")
    public ResponseData deleteAuthor(@PathVariable String isbn){
        return authorService.deleteAuthor(isbn);
    }


}
