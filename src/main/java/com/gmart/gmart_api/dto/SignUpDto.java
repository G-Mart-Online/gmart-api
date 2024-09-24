package com.gmart.gmart_api.dto;

public record SignUpDto(String firstName, String lastName, String username, String email, char[] password) {
}