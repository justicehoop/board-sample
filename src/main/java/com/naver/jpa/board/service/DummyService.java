package com.naver.jpa.board.service;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.naver.jpa.board.domain.Board;
import com.naver.jpa.board.domain.Member;
import com.naver.jpa.board.repository.ArticleRepository;
import com.naver.jpa.board.repository.BoardRepository;
import com.naver.jpa.board.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class DummyService {
  private final BoardRepository boardRepository;

  private void createBoards() {
    for (int i = 0; i < 3; i++) {
      boardRepository.save(Board.of("name" + i, "", Board.BoardType.NORMAL));
    }
  }

  private final MemberRepository memberRepository;

  private void createMember() {
    for (int i = 0; i < 3; i++) {
      memberRepository.save(Member.of("userId" + i, "nickname" + i, "name" + i, "password"));
    }
  }

  private final ArticleRepository articleRepository;

  @PostConstruct
  public void afterInitialized() {
    createBoards();
    createMember();
  }
}
