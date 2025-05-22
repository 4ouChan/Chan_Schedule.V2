package com.example.schedulev2.dto;

import com.example.schedulev2.entity.UserEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private final Long userId;

    private final String userName;

    private final String email;

    private final LocalDateTime createDate;

    private final LocalDateTime updateDate;


    public UserResponseDto(
            Long userId,
            String userName,
            String email,
            LocalDateTime createDate,
            LocalDateTime updateDate
    ) {
        this.userId = userId;
        this.userName = userName;
        this.email = email;
        this.createDate = createDate;
        this.updateDate = updateDate;
    }

    public static UserResponseDto toUserDto(UserEntity userEntity) {
        return new UserResponseDto(
                userEntity.getUserId(),
                userEntity.getUserName(),
                userEntity.getEmail(),
                userEntity.getCreateDate(),
                userEntity.getUpdateDate()
        );
    }

}
