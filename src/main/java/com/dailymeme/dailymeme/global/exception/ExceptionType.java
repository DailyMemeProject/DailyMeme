package com.dailymeme.dailymeme.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionType {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,  "해당 유저의 정보를 찾을 수 없습니다."),
    WRONG_PASSWORD(HttpStatus.FORBIDDEN, "잘못된 비밀번호 입니다."),
    DELETED_USER(HttpStatus.BAD_REQUEST, "탈퇴한 유저입니다."),
    NOT_FOUND_POST_OR_NOT_MATCH_USER(HttpStatus.BAD_REQUEST, "존재하지 않는 게시물 이거나, 작성자가 아닙니다.");

    private final HttpStatus httpStatus;
    private String message;

    ExceptionType(HttpStatus httpStatus,  String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }



}
