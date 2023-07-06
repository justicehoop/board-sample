package com.naver.jpa.board.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.naver.jpa.board.IntegrationTestSupport;
import com.naver.jpa.board.domain.Member;

public class MemberRepositoryTest extends IntegrationTestSupport {
  @Autowired
  private MemberRepository memberRepository;

  public static class Fixture {
    public static Member createMember(String userId, String name) {
      return Member.of(userId, "nickname", name, "password");
    }
  }

  @Test
  public void testSaveUser() {
    //Given
    Member toCreateMember = Fixture.createMember("userId", "name");
    Member member = memberRepository.save(toCreateMember);

    assertNotNull(member);
    assertEquals(toCreateMember.getUserId(), member.getUserId());
    assertEquals(toCreateMember.getName(), member.getName());
  }

  @Test
  public void testFindByIdOrNull() {
    //Given
    Member toCreateMember = Fixture.createMember("userId", "name");
    memberRepository.save(toCreateMember);

    //When
    Optional<Member> savedUserOptional = memberRepository.findById(toCreateMember.getId());

    Member savedMember = savedUserOptional.get();

    //Them
    assertNotNull(savedMember);
    assertEquals(toCreateMember.getId(), savedMember.getId());
    assertEquals(toCreateMember.getUserId(), savedMember.getUserId());
    assertEquals(toCreateMember.getName(), savedMember.getName());
  }

  @Test
  public void testDelete() {
    //Given
    Member toCreateMember = Fixture.createMember("userId", "name");
    memberRepository.save(toCreateMember);

    //When
    memberRepository.deleteById(toCreateMember.getId());

    //Then
    assertFalse(memberRepository.findById(toCreateMember.getId()).isPresent());
  }
}