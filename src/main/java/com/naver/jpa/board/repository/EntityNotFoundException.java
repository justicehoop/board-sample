package com.naver.jpa.board.repository;

public class EntityNotFoundException extends RuntimeException {

  public EntityNotFoundException(String msg) {
    super(msg);
  }

  public EntityNotFoundException(String msg, Throwable t) {
    super(msg, t);
  }
}
