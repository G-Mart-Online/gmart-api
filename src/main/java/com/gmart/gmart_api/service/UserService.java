package com.gmart.gmart_api.service;

import com.gmart.gmart_api.dto.CredentialsDto;
import com.gmart.gmart_api.dto.SignUpDto;
import com.gmart.gmart_api.dto.UserDto;
import com.gmart.gmart_api.exceptions.AppException;
import com.gmart.gmart_api.model.User;
import com.gmart.gmart_api.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;
import java.util.Set;

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
        UserDto userDto = new UserDto();
        userDto.setId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());
        userDto.setRoles(user.getRoles());

        return userDto;
    }

    private User signUpDtoToUser(SignUpDto signUpDto) {
        User user = new User();
        user.setUsername(signUpDto.username());
        user.setFirstName(signUpDto.firstName());
        user.setLastName(signUpDto.lastName());
        user.setEmail(signUpDto.email());
        if (signUpDto.roles() != null && !signUpDto.roles().isEmpty()) {
            user.setRoles(signUpDto.roles());
        } else {
            // Assign default role if roles are not provided in DTO
            user.setRoles(Set.of("ROLE_USER"));
        }
        return user;
    }


//    නිමේෂ් ගොතයා කෝඩ් එක වෙනස් කරොත් මැරෙන්නඩ් හදන නිසා කොමෙන්ට් කරලා තිබ්බා

//    private UserDto toUserDto(User user) {
//        UserDto userDto = modelMapper.map(user,UserDto.class);
//        return userDto;
//    }
//
//    private User signUpDtoToUser(SignUpDto signUpDto) {
//        User user = modelMapper.map(signUpDto,User.class);
//        return user;
//    }

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

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByUsername(credentialsDto.username())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
            return toUserDto(user);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return toUserDto(user);
    }

}
