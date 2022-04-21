package com.example.ebshop.common.exception;

import com.example.ebshop.dto.request.AuthorRequest;

public class AuthorException {
    public static void checkException(AuthorRequest authorRequest) throws HandleException {

        if (authorRequest.getName() == null)
            throw new HandleException("Name is null", "000");
        if (authorRequest.getIsbn() == null)
            throw new HandleException("isbn is null", "111");

    }
}
