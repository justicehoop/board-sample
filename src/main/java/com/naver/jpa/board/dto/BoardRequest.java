package com.naver.jpa.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.naver.jpa.board.domain.Board;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardRequest {
  private String name;
  private String description;
  private Board.BoardType boardType;

  public static BoardRequest of(String name, String description, Board.BoardType boardType) {
    return new BoardRequest(name, description, boardType);
  }
}
