package com.gmart.gmart_api.controller;

import com.gmart.gmart_api.model.User;
import com.gmart.gmart_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
