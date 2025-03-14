package com.bauk.deliveryrequest.exceptions;

public class InvalidStatusException extends BaseException {

    public InvalidStatusException(String message) {
        super(message, 400);
    }

}
