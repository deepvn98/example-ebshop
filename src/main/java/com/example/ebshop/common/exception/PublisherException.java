package com.example.ebshop.common.exception;

import com.example.ebshop.dto.request.PublisherRequest;

public class PublisherException {
    public static void checkException(PublisherRequest publisherRequest) throws HandleException {

        if (publisherRequest.getName() == null)
            throw new HandleException("Name is null", "000");
        if (publisherRequest.getIsbn() == null)
            throw new HandleException("isbn is null", "111");

    }

}
