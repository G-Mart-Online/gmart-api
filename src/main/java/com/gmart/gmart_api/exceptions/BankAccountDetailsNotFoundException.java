package com.gmart.gmart_api.exceptions;

public class BankAccountDetailsNotFoundException extends RuntimeException {
    public BankAccountDetailsNotFoundException(String message) {
        super(message);
    }
}
