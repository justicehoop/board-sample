package com.naver.jpa.board.fixture;

import com.naver.jpa.board.domain.Member;

public class UserCreator {

  public static Member create(String userId, String nickname) {
    return Member.of(userId, nickname, nickname, "password");
  }

}
