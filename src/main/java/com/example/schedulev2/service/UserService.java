package com.example.schedulev2.service;

import com.example.schedulev2.dto.UserResponseDto;
import com.example.schedulev2.entity.UserEntity;
import com.example.schedulev2.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto createUser(String userName, String email, String password) {

        UserEntity userEntity = new UserEntity(userName, email, password);

        userRepository.save(userEntity);

        UserResponseDto userResponseDto = new UserResponseDto(
                userEntity.getUserId(),
                userEntity.getUserName(),
                userEntity.getEmail(),
                userEntity.getCreateDate(),
                userEntity.getUpdateDate()
        );

        return userResponseDto;
    }

    public List<UserResponseDto> findAllSchedule() {
        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toUserDto)
                .toList();
    }
}
