package com.naver.jpa.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.naver.jpa.board.domain.Article;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ArticleResponse {
  private Long id;
  private BoardResponse board;
  private String title;
  private String content;
  private Long viewCount;

  public static ArticleResponse fromArticle(Article article) {
    return ArticleResponse.builder()
      .id(article.getId())
      .board(BoardResponse.from(article.getBoard()))
      .title(article.getTitle())
      .content(article.getContent())
      .viewCount(article.getViewCount())
      .build();
  }

}
