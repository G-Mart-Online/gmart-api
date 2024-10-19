package com.gmart.gmart_api.exceptions;

public class LogInException extends RuntimeException {
    public LogInException(String message) {
        super(message);
    }

    public LogInException(String message, Throwable cause) {
        super(message, cause);
    }
}
