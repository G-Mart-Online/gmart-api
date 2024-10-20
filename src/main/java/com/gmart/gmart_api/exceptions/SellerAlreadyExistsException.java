package com.gmart.gmart_api.exceptions;

public class SellerAlreadyExistsException extends RuntimeException{
    public SellerAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SellerAlreadyExistsException(String message) {
        super(message);
    }
}
