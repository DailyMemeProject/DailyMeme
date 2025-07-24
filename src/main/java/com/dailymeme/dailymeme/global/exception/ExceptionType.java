package com.dailymeme.dailymeme.global.exception;

import org.springframework.http.HttpStatus;

public enum ExceptionType {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,  "해당 유저의 정보를 찾을 수 없습니다."),
    WRONG_PASSWORD(HttpStatus.FORBIDDEN, "잘못된 비밀번호 입니다.");

    private final HttpStatus httpStatus;
    private String message;

    ExceptionType(HttpStatus httpStatus,  String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }



}
