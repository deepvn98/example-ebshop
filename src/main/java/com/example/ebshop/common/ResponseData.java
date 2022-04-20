package com.example.ebshop.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class ResponseData {
    private Object object;
    private String message;
    private String code;
    private HttpStatus httpStatus;
}
