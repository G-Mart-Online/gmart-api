package com.gmart.gmart_api.exceptions;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final HttpStatus httpStatus;
    private final String errorCode;

    private AppException(Builder builder) {
        super(builder.message);
        this.httpStatus = builder.httpStatus;
        this.errorCode = builder.errorCode;
    }

    public AppException(String message, HttpStatus httpStatus, String errorCode) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }

    public AppException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorCode = null;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public static class Builder {
        private String message;
        private HttpStatus httpStatus;
        private String errorCode;

        public Builder message(String message) {
            this.message = message;
            return this;
        }

        public Builder httpStatus(HttpStatus httpStatus) {
            this.httpStatus = httpStatus;
            return this;
        }

        public Builder errorCode(String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public AppException build() {
            return new AppException(this);
        }
    }
}
