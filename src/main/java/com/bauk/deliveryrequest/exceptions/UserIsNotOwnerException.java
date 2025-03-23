package com.bauk.deliveryrequest.exceptions;

public class UserIsNotOwnerException extends BaseException {
    public UserIsNotOwnerException(String message) {
        super(message, 403);
    }
}
