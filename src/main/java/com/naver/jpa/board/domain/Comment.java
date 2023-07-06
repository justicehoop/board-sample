package com.naver.jpa.board.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PACKAGE)
@Getter
public class Comment extends BaseEntity<Long>  {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter
  @Getter
  private Long id;
  @JoinColumn(name="article_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Article article;
  private String content;
  private Long viewCount = 0L;
  private Long likeCount = 0L;
  @JoinColumn(name="parent_comment_id")
  @ManyToOne(fetch = FetchType.LAZY)
  private Comment parentComment;
  private int depth;
  @OneToMany(mappedBy = "parentComment")
  private List<Comment> comments = new ArrayList<>();

  public Comment increaseViewCount() {
    this.viewCount++;
    return this;
  }

  public Comment increaseLikeCount() {
    this.likeCount++;
    return this;
  }

  public Comment addComment(Comment comment) {
    this.comments.add(comment);
    return this;
  }

    public static Comment of(Long id, Article article, String content) {
      return new Comment(id, article, content, 0L, 0L, null, 0, new ArrayList<>());
    }

}
