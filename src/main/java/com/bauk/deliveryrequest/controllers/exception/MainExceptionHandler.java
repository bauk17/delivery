package com.bauk.deliveryrequest.controllers.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bauk.deliveryrequest.exceptions.BaseException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class MainExceptionHandler {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(System.currentTimeMillis(), e.getStatusCode(),
                e.getMessage(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(e.getStatusCode()).body(error);
    }

}
