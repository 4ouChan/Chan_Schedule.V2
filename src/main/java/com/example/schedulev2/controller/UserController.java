package com.example.schedulev2.controller;

import com.example.schedulev2.dto.UserRequestDto;
import com.example.schedulev2.dto.UserResponseDto;
import com.example.schedulev2.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> createUserAPI(@RequestBody UserRequestDto dto) {

        UserResponseDto userResponseDto = userService.createUser(dto.getUserName(), dto.getEmail(), dto.getPassword());

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

}
