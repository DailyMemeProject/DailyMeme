package com.dailymeme.dailymeme.domain.user.dto.info;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoListResponseDto {

    private String userName;

    private String email;

    private Boolean mailSendAgree;

    //private String profileImage;


}
