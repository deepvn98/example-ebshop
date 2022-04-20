package com.example.ebshop.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PublisherRequest {
    private String isbn;
    private String name;

}
