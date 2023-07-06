package com.naver.jpa.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MemberJoinRequest {
  private String userId;
  private String nickname;
  private String name;
  private String password1;
  private String password2;
}
