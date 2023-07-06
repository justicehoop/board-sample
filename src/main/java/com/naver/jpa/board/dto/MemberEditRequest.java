package com.naver.jpa.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class MemberEditRequest {
  private String nickname;
  private String name;

}
