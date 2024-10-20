package com.gmart.gmart_api.controller;

import com.gmart.gmart_api.config.UserAuthenticationProvider;
import com.gmart.gmart_api.dto.LogInDto;
import com.gmart.gmart_api.dto.RegisterUserDto;
import com.gmart.gmart_api.dto.UserDto;
import com.gmart.gmart_api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final UserService userService;

    private final UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    public AuthController(UserService userService, UserAuthenticationProvider userAuthenticationProvider) {
        this.userService = userService;
        this.userAuthenticationProvider = userAuthenticationProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegisterUserDto registerUserDto) {

        UserDto createdUser = userService.register(registerUserDto);
        createdUser.setToken(userAuthenticationProvider.createToken(createdUser));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid LogInDto logInDto) {
        UserDto userDto = userService.login(logInDto);
        userDto.setToken(userAuthenticationProvider.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

}
