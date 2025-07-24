package com.dailymeme.dailymeme.global.exception;

public class DeletedUserException extends CustomException {
    public DeletedUserException(final ExceptionType type) {
        super(type);
    }
}
