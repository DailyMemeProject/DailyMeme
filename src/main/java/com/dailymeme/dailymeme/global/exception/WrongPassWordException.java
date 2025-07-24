package com.dailymeme.dailymeme.global.exception;

public class WrongPassWordException extends CustomException {
    public WrongPassWordException(final ExceptionType type) {
        super(type);
    }
}
