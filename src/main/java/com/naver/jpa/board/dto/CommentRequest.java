package com.naver.jpa.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommentRequest {
  private String content;

  public static CommentRequest of(String content) {
    return new CommentRequest(content);
  }
}
