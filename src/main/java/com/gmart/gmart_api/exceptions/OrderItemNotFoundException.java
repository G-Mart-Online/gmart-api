package com.gmart.gmart_api.exceptions;

public class OrderItemNotFoundException extends RuntimeException{
    public OrderItemNotFoundException(String message) {super(message);}
    public OrderItemNotFoundException(String message, Throwable cause) {super(message,cause);}
}
