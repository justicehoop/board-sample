package com.naver.jpa.board.exception;

public class ResourceNotFoundException extends RuntimeException{

  public ResourceNotFoundException(String msg) {
    super(msg);
  }

  public ResourceNotFoundException(String msg, Throwable t) {
    super(msg, t);
  }
}
