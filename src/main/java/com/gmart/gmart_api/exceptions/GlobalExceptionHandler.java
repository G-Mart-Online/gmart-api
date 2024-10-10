package com.gmart.gmart_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Global exception handler for any other exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception exception) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

        var result = CustomException.of(
                "An unexpected error occurred: " + exception.getMessage(),
                httpStatus,
                ZonedDateTime.now());
        return new ResponseEntity<>(result, httpStatus);
    }

    @ExceptionHandler(value = {EmailAlreadyExistException.class})
    public ResponseEntity<Object> handleEmailNotFoundException(
            EmailAlreadyExistException exception
    ) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        var result = CustomException.of(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now());
        return new ResponseEntity<>(result
                , httpStatus);
    }

    @ExceptionHandler(value = {LogInException.class})
    public ResponseEntity<Object> handleLogInException(
            LogInException exception
    ) {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        var result = CustomException.of(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now());
        return new ResponseEntity<>(result, httpStatus);
    }

    @ExceptionHandler(value = {UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFoundException(
            UserNotFoundException exception
    ) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        var result = CustomException.of(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now());
        return new ResponseEntity<>(result, httpStatus);
    }

    @ExceptionHandler(value = {OrderNotFoundException.class})
    public ResponseEntity<Object> handleOrderNotFoundException(
            OrderNotFoundException exception
    ) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        var result = CustomException.of(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now());
        return new ResponseEntity<>(result, httpStatus);
    }

    @ExceptionHandler(value = {OrderItemNotFoundException.class})
    public ResponseEntity<Object> handleOrderItemNotFoundException(
            OrderItemNotFoundException exception
    ) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        var result = CustomException.of(
                exception.getMessage(),
                httpStatus,
                ZonedDateTime.now());
        return new ResponseEntity<>(result, httpStatus);
    }

}
