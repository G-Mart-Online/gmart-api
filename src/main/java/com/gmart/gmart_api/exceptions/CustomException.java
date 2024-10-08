package com.gmart.gmart_api.exceptions;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.springframework.http.HttpStatus;
//
//import java.time.ZonedDateTime;
//
//@Data
//@AllArgsConstructor
//public class CustomException {
//
//    private final String message;
//    private final HttpStatus httpStatus;
//    private final ZonedDateTime timeStamp;
//
//}



import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
public class CustomException {
    private final String message;
    private final HttpStatus httpStatus;
    private final String timeStamp;

    // You can also add a static factory method to build the CustomException with formatted timestamp
    public static CustomException of(String message, HttpStatus status, ZonedDateTime timeStamp) {
        return new CustomException(message, status, timeStamp.format(DateTimeFormatter.ISO_ZONED_DATE_TIME));
    }
}
