package com.naver.jpa.board.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.util.StringUtils;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@EqualsAndHashCode(of = {"id"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter(AccessLevel.PACKAGE)
@Getter
public class Member extends BaseEntity<Long>{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter
  @Getter
  private Long id;
  private String userId;
  private String nickname;
  private String name;
  private String password;
  public Member edit(String nickname, String name) {
    this.nickname = nickname;
    this.name = name;
    return this;
  }

  public boolean isEqualOf(Long id, String userId) {
    return this.id.equals(id) && this.userId.equals(userId);
  }

  public Member changePassword(String password1, String password2) {
    if (StringUtils.hasText(password1) && password1.equals(password2)) {
      throw new IllegalArgumentException("password1 and password2 must be same!");
    }
    return this;
  }

  public static Member of(String userId, String nickname, String name, String password) {
    return Member.builder()
      .userId(userId)
      .nickname(nickname)
      .name(name)
      .password(password)
      .build();
  }

}
