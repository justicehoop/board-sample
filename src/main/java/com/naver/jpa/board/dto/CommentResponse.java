package com.naver.jpa.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import com.naver.jpa.board.domain.Comment;

@Getter
@Builder
@AllArgsConstructor
public class CommentResponse {
  private Long id;
  private ArticleResponse article;
  private String content;
  private Long viewCount;
  private Long likeCount;

  public static CommentResponse from(Comment comment) {
    return CommentResponse.builder()
      .id(comment.getId())
      .article(ArticleResponse.fromArticle(comment.getArticle()))
      .content(comment.getContent())
      .viewCount(comment.getViewCount())
      .likeCount(comment.getLikeCount())
      .build();
  }
}
