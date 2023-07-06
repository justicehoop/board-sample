package com.naver.jpa.board.service.id;

public interface IdGenerator<T> {
  T generate();
}
