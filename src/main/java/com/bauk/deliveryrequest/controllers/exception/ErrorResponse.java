package com.bauk.deliveryrequest.controllers.exception;

public class ErrorResponse {
    private String message;
    private int statusCode;
    private String error;
    private String path;
    private Long timestamp;

    public ErrorResponse() {
    }

    public ErrorResponse(Long timestamp, Integer statusCode, String error, String message, String path) {
        super();
        this.message = message;
        this.statusCode = statusCode;
        this.error = error;
        this.path = path;
        this.timestamp = timestamp;

    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
