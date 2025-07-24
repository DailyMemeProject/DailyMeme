package com.dailymeme.dailymeme.domain.user.dto.signup;

import lombok.Getter;

@Getter
public class UserSignupRequestDto {

    private String username;
    private String email;
    private String password;
    private Boolean mailSendAgree;

}
