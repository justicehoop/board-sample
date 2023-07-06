package com.naver.jpa.board.service;

import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.naver.jpa.board.domain.Member;
import com.naver.jpa.board.repository.MemberRepository;

@Transactional
@RequiredArgsConstructor
public class MemberResolver {

  private final MemberRepository memberRepository;

  public Member resolve() {
    return memberRepository.findAll().stream()
      .findFirst()
      .orElseThrow(() -> new IllegalArgumentException(""));
  }
}
