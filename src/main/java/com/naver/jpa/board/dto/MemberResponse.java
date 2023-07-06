package com.naver.jpa.board.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import com.naver.jpa.board.domain.Member;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
public class MemberResponse {
  private Long id;
  private String userId;
  private String nickname;
  private String name;

  public static MemberResponse from(Member member) {
    return MemberResponse.builder()
      .id(member.getId())
      .userId(member.getUserId())
      .nickname(member.getNickname())
      .name(member.getName())
      .build();
  }
}
