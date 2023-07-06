package com.naver.jpa.board.service.id;

public class NullIdGenerator implements IdGenerator<Long> {
  @Override
  public Long generate() {
    return null;
  }
}