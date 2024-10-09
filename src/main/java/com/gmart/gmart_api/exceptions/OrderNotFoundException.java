package com.gmart.gmart_api.exceptions;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String message) {super(message);}
    public OrderNotFoundException(String message, Throwable cause) {super(message,cause);}
}
