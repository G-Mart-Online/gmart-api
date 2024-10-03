package com.gmart.gmart_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;


@Document(collection = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String userId;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    private Set<String> roles;

}












