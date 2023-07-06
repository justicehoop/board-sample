package com.naver.jpa.board.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.naver.jpa.board.dto.MemberJoinRequest;
import com.naver.jpa.board.fixture.UserCreator;
import com.naver.jpa.board.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
public class MemberValidatorTest {

  @Mock
  private MemberRepository memberRepository;
  @InjectMocks
  private MemberService.MemberValidator memberValidator;

  @Test
  public void testValidate_should_throw_nickname_already_exists() {

    //Given
    final String duplicatedNickname = "duplicatedNickname";

    given(memberRepository.findByNickname(duplicatedNickname))
      .willReturn(UserCreator.create("userid", duplicatedNickname));

    //When & Then
    assertThrows(IllegalArgumentException.class, () ->
      memberValidator.validate(MemberJoinRequest
        .builder()
        .userId("userId")
        .nickname(duplicatedNickname)
        .name("test")
        .password1("password")
        .password2("password")
        .build())
    );

  }

  @Test
  public void testValidate_should_throw_userId_already_exists() {

    //Given
    final String duplicatedUserId = "duplicatedUserId";

    given(memberRepository.findByUserId(duplicatedUserId))
      .willReturn(UserCreator.create(duplicatedUserId, "nickname"));

    //When & Then
    assertThrows(IllegalArgumentException.class, () ->
      memberValidator.validate(MemberJoinRequest
        .builder()
        .userId(duplicatedUserId)
        .nickname("nickname")
        .name("test")
        .password1("password")
        .password2("password")
        .build())
    );

  }

}