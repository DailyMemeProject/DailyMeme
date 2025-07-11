package com.dailymeme.dailymeme.domain.user.dto.signup;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSignupResponseDto {

    private Long id;
    private String username;
    private String email;
    private Boolean mail_send_agree;
}
