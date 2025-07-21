package com.dailymeme.dailymeme.domain.user.dto.info;

import com.dailymeme.dailymeme.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserInfoUpdateResponseDto {
    private String userName;

    private Boolean mailSendAgree;

    public UserInfoUpdateResponseDto(User user) {
        this.userName = user.getUserName();
        this.mailSendAgree = user.getMailSendAgree();
    }
}
