package com.example.schedulev2.controller;

import com.example.schedulev2.dto.*;
import com.example.schedulev2.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/login")
    public ResponseEntity<String> loginAPI(@RequestBody LoginRequestDto dto, HttpSession session) {

        userService.loginUser(session, dto.getEmail(), dto.getPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findAllUserAPI() {

        List<UserResponseDto> allSchedule = userService.findAllSchedule();

        return new ResponseEntity<>(allSchedule, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDto> findByIdUserAPI(@PathVariable Long userId) {

        UserResponseDto byIdUser = userService.findByIdUser(userId);

        return new ResponseEntity<>(byIdUser, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UpdateUserPasswordResponseDto> updateUserPasswordAPI(
            @PathVariable Long userId,
            @RequestBody UpdateUserPasswordRequestDto dto
    ) {
        UpdateUserPasswordResponseDto userResponseDto = userService.updateUser(userId, dto.getUserPassword(), dto.getNewUserPassword());

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<List<UserResponseDto>> deleteUserAPI(@PathVariable Long userId, @RequestBody UserRequestDto dto) {

        List<UserResponseDto> userResponseDtos = userService.deleteUser(userId, dto.getPassword());

        return new ResponseEntity<>(userResponseDtos, HttpStatus.OK);
    }


}
