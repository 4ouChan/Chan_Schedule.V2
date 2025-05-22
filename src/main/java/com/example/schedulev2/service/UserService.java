package com.example.schedulev2.service;

import com.example.schedulev2.dto.ScheduleResponseDto;
import com.example.schedulev2.dto.UpdateUserPasswordResponseDto;
import com.example.schedulev2.dto.UserResponseDto;
import com.example.schedulev2.entity.ScheduleEntity;
import com.example.schedulev2.entity.UserEntity;
import com.example.schedulev2.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

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

    public UserResponseDto findByIdUser(Long userId) {
        Optional<UserEntity> findByIdUser = userRepository.findById(userId);

        UserEntity responseDto = findByIdUser.get();

        return new UserResponseDto(
                responseDto.getUserId(),
                responseDto.getUserName(),
                responseDto.getEmail(),
                responseDto.getCreateDate(),
                responseDto.getUpdateDate()
        );
    }

    @Transactional
    public UpdateUserPasswordResponseDto updateUser(Long userId, String userPassword, String newUserPassword) {
        // 유저 단건 조회
        Optional<UserEntity> findByIdUser = userRepository.findById(userId);

        // 옵셔널 객체를 UserEntity에 담기
        UserEntity checkPassword = findByIdUser.get();

        // 입력 비밀번호와 기존 비밀번호가 같은지 검증하고 틀릴 시 Exception발생
        if (userPassword.equals(checkPassword.getPassword())) {
            checkPassword.setPassword(newUserPassword);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 업데이트 성공 메시지를 반환
        String message = "업데이트 완료";
        UpdateUserPasswordResponseDto updateUserPasswordResponseDto = new UpdateUserPasswordResponseDto(message);

        return updateUserPasswordResponseDto;
    }

    public List<UserResponseDto> deleteUser(Long userId) {

        Optional<UserEntity> findByIdUser = userRepository.findById(userId);

        UserEntity userEntity = findByIdUser.get();

        userRepository.delete(userEntity);

        return userRepository.findAll()
                .stream()
                .map(UserResponseDto::toUserDto)
                .toList();
    }
}
