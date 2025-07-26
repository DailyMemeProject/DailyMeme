package com.dailymeme.dailymeme.global.exception;

public class NotFoundPostOrNotMatchUserException extends CustomException{
    public NotFoundPostOrNotMatchUserException(final ExceptionType type) {
        super(type);
    }
}
