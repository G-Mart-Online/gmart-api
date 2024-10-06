package com.gmart.gmart_api.service;

import com.gmart.gmart_api.dto.LogInDto;
import com.gmart.gmart_api.dto.RegisterUserDto;
import com.gmart.gmart_api.dto.UserDto;
import com.gmart.gmart_api.exceptions.AppException;
import com.gmart.gmart_api.model.User;
import com.gmart.gmart_api.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.CharBuffer;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final ModelMapper modelMapper;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = mapper;

    }

    private UserDto toUserDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        return userDto;
    }

    private User signUpDtoToUser(RegisterUserDto signUpDto) {
        User user = modelMapper.map(signUpDto, User.class);
        return user;
    }

    public UserDto register(RegisterUserDto userDto) {
        Optional<User> optionalUser = userRepository.findByUsername(userDto.getUsername());

        if (optionalUser.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        User user = signUpDtoToUser(userDto);

        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(userDto.getPassword())));
        User savedUser = userRepository.save(user);

        return toUserDto(savedUser);
    }

    public UserDto login(LogInDto logInDto) {
        User user = userRepository.findUserByEmail(logInDto.getEmail())
                .orElseThrow(() -> new AppException("Email or Password is incorrect", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(logInDto.getPassword()), user.getPassword())) {
            return toUserDto(user);
        }
        throw new AppException("Email or Password is incorrect", HttpStatus.BAD_REQUEST);
    }

    //delete user by Id
    @Transactional
    public String deleteUserById(String userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return "User deleled successfully";
        } else {
            throw new UsernameNotFoundException("User with ID " + userId + " not found");
        }
    }


    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return toUserDto(user);
    }

}
