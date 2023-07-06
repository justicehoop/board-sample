package com.naver.jpa.board.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.naver.jpa.board.domain.Member;
import com.naver.jpa.board.dto.MemberEditRequest;
import com.naver.jpa.board.dto.MemberJoinRequest;
import com.naver.jpa.board.dto.MemberResponse;
import com.naver.jpa.board.exception.ResourceNotFoundException;
import com.naver.jpa.board.fixture.UserCreator;
import com.naver.jpa.board.repository.MemberRepository;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

  @Mock
  private MemberRepository memberRepository;
  @Mock
  private MemberService.MemberValidator memberValidator;

  @InjectMocks
  private MemberService memberService;

  @Test
  public void testJoin_should_be_save_successfully() {

    //Given
    MemberJoinRequest request = MemberJoinRequest.builder()
      .userId("userId")
      .name("name")
      .nickname("nickname")
      .password1("password")
      .password2("password")
      .build();
    Member expected = UserCreator.create( "userId", "name");
    given(memberRepository.save(any())).willReturn(expected);

    //When
    MemberResponse actual = memberService.join(request);

    //Then
    assertEquals(expected.getUserId(), actual.getUserId());
    assertEquals(expected.getNickname(), actual.getNickname());
  }

  @Test
  public void testGetOne_should_be_return_successfully() {

    //Given
    Member expected = UserCreator.create("userId", "name");
    given(memberRepository.findById(any())).willReturn(Optional.of(expected));

    //When
    MemberResponse actual = memberService.getOne(expected.getId());

    //Then
    assertEquals(expected.getUserId(), actual.getUserId());
    assertEquals(expected.getNickname(), actual.getNickname());
  }

  @Test
  public void testGetOne_should_be_throw_exception_when_user_does_not_exist() {

    //Given
    Member expected = UserCreator.create( "userId", "name");
    given(memberRepository.findById(any())).willReturn(Optional.empty());

    //When
    assertThrows(ResourceNotFoundException.class, () -> {
      memberService.getOne(expected.getId());
    });
  }

  @Test
  public void testEdit_should_be_throw_exception_when_user_does_not_exist() {
    //Given
    Member expected = UserCreator.create( "userId", "name");
    given(memberRepository.findById(any())).willReturn(Optional.empty());

    //When
    assertThrows(ResourceNotFoundException.class, () -> {
      memberService.edit(expected.getId(), MemberEditRequest.of("changedNickname", "changedName"));
    });
  }

  @Test
  public void testEdit_should_be_edit_successfully() {
    //Given
    MemberEditRequest editRequest = MemberEditRequest.of("changedNickname", "changedName");
    Member expected = UserCreator.create( "userId", "name");
    given(memberRepository.findById(any())).willReturn(Optional.of(expected));
    given(memberRepository.save(expected)).willReturn(expected.edit(editRequest.getNickname(), editRequest.getName()));

    //When
    MemberResponse actual = memberService.edit(expected.getId(), editRequest);

    //Then
    assertEquals(expected.getId(), actual.getId());
    assertEquals(editRequest.getNickname(), actual.getNickname());
    assertEquals(editRequest.getName(), actual.getName());
  }

}
