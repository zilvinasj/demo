package com.demo.top.exception;

public class ErrorBody {
    private String message;

    public ErrorBody(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
