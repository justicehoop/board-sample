package com.naver.jpa.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import com.naver.jpa.board.domain.Board;

@Getter
@AllArgsConstructor
@Builder
public class BoardResponse {
  private Long id;
  private String name;
  private String description;

  public static BoardResponse from(Board board) {
    return BoardResponse.builder()
      .id(board.getId())
      .name(board.getName())
      .description(board.getDescription())
      .build();
  }
}
