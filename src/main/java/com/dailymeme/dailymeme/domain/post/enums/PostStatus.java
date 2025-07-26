package com.dailymeme.dailymeme.domain.post.enums;

import lombok.Getter;

@Getter
public enum PostStatus {

    ACTIVE("active"),
    REPORT("report"),
    DELETED("deleted");

    private final String value;

    PostStatus(String value) {
        this.value = value;
    }

}
