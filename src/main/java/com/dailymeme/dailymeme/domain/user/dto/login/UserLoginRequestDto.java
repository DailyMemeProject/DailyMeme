package com.dailymeme.dailymeme.domain.user.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserLoginRequestDto {

    private String email;
    private String password;
}
