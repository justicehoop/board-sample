package com.naver.jpa.board.fixture;

import org.springframework.stereotype.Component;

import com.naver.jpa.board.domain.Board;

public abstract class BoardCreator {

  public static Board create(Long id, String name) {
    return Board.of(name, "", Board.BoardType.NORMAL);
  }
}
