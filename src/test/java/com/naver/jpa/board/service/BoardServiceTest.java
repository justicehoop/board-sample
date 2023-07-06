package com.naver.jpa.board.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.naver.jpa.board.repository.BoardRepository;

@ExtendWith(MockitoExtension.class)
public class BoardServiceTest {

  @Mock
  private BoardRepository boardRepository;

  @Mock
  private BoardService.BoardValidator boardValidator;

  @InjectMocks
  private BoardService boardService;

  @Test
  public void test() {



  }

}