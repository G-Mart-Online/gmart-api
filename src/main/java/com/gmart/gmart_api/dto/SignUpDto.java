package com.gmart.gmart_api.dto;

import java.util.Set;

public record SignUpDto(String firstName, String lastName, String username, String email, String password, Set<String> roles) {
}