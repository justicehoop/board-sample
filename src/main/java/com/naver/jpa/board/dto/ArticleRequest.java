package com.naver.jpa.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ArticleRequest {
  private String title;
  private String content;

  public static ArticleRequest of(String title, String content) {
    return new ArticleRequest(title, content);
  }

}
