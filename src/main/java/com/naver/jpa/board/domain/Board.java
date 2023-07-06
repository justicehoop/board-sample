package com.naver.jpa.board.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@AllArgsConstructor
@Builder
@Setter(AccessLevel.PACKAGE)
@Getter
public class Board extends BaseEntity<Long> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter
  @Getter
  private Long id;
  private String name;
  private String description;
  private Long totalArticleCount = 0L;
  private BoardType boardType;
  @OneToMany(mappedBy = "board")
  private List<Article> articles = new ArrayList<>();

  public enum BoardType {
    NORMAL;
  }

  public Board edit(String name, String description) {
    this.name = name;
    this.description = description;
    return this;
  }

  public static Board of(String name, String description, BoardType boardType) {
    return new Board(null, name, description, 0L, boardType, new ArrayList<>());
  }

}
