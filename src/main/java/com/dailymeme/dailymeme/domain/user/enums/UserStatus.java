package com.dailymeme.dailymeme.domain.user.enums;

import lombok.Getter;

@Getter
public enum UserStatus {

    ACTIVE("active"),
    DELETED("deleted");

    private final String name;
    UserStatus(String name) {this.name = name;}
}
