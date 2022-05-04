package com.example.ebshop.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BookAuthorRequest {
    private int bookId;
    private int authorId;

    public BookAuthorRequest(int bookId, int authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }
}
