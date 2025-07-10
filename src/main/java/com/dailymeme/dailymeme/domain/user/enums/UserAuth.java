package com.dailymeme.dailymeme.domain.user.enums;

import lombok.Getter;

@Getter
public enum UserAuth {

    USER("user"),
    ADMIN("admin");

    private String name;
    UserAuth(String name) {this.name = name;}

}
