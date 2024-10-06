package com.gmart.gmart_api.controller;

import com.gmart.gmart_api.model.User;
import com.gmart.gmart_api.repository.UserRepository;
import com.gmart.gmart_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;


    @Secured({"ROLE_ADMIN", "ROLE_SELLER"})
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<String> deleteUserById(@PathVariable String uid) {
        var result = userService.deleteUserById(uid);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }
}
