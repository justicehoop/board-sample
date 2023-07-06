package com.naver.jpa.board.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PACKAGE)
@Getter
public class Article extends BaseEntity<Long> {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter
  @Getter
  protected Long id;
  @JoinColumn(name="board_id")
  @ManyToOne(fetch = FetchType.LAZY)
  protected Board board;
  protected String title;
  protected String content;
  protected Long viewCount = 0L;
  @Enumerated(EnumType.STRING)
  protected ArticleType articleType = ArticleType.NORMAL;
  @OneToMany
  protected List<Comment> comments = new ArrayList<>();

  public enum ArticleType {
    NORMAL
  }

  public static Article of( Board board, String title, String content) {
    return new Article(null,board, title, content, 0L, ArticleType.NORMAL, new ArrayList<>());
  }
}
