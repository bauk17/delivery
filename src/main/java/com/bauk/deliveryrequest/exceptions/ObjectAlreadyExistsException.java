package com.bauk.deliveryrequest.exceptions;

public class ObjectAlreadyExistsException extends BaseException {
    public ObjectAlreadyExistsException(String message) {
        super(message, 409);
    }
}
