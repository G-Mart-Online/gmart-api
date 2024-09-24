package com.gmart.gmart_api.service;

import com.gmart.gmart_api.dto.SignUpDto;
import com.gmart.gmart_api.dto.UserDto;
import com.gmart.gmart_api.exceptions.AppException;
import com.gmart.gmart_api.model.User;
import com.gmart.gmart_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private UserDto toUserDto(User user) {
        // Convert User entity to UserDto manually
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    private User signUpDtoToUser(SignUpDto signUpDto) {
        // Convert SignUpDto to User manually
        User user = new User();
        user.setUsername(signUpDto.username());
        user.setFirstName(signUpDto.firstName());
        user.setLastName(signUpDto.lastName());
        user.setEmail(signUpDto.email());
        return user;
    }

    public UserDto register(SignUpDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.username());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = signUpDtoToUser(userDto);

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.password())));
        User savedUser = userRepository.save(user);

        return toUserDto(savedUser);
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return toUserDto(user);
    }


}
