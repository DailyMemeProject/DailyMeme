package com.dailymeme.dailymeme.domain.user.dto.info;

import lombok.Getter;

@Getter
public class UserInfoResponseDto {

    private String userName;

    private String email;

    public UserInfoResponseDto(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }
}
