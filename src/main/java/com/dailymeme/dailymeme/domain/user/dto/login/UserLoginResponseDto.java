package com.dailymeme.dailymeme.domain.user.dto.login;

import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private Long id;
    private String userName;
    private String email;

    //private String accessToken;
    //private String refreshToken;

    public UserLoginResponseDto(Long id, String username, String email) {
        this.id = id;
        this.userName = username;
        this.email = email;
    }
}
