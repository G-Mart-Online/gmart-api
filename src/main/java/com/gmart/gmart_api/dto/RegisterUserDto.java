package com.gmart.gmart_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
}
