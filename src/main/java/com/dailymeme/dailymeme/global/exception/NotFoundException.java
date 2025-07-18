package com.dailymeme.dailymeme.global.exception;

public class NotFoundException extends CustomException{

  public NotFoundException(final ExceptionType type) {
    super(type);
  }

}
