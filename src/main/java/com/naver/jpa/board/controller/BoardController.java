package com.naver.jpa.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.naver.jpa.board.dto.BoardResponse;
import com.naver.jpa.board.service.BoardService;

@RequiredArgsConstructor
@RequestMapping("/v1/boards")
@RestController
public class BoardController {
  private final BoardService boardService;

  @GetMapping("/{id}")
  public BoardResponse getOne(@PathVariable("iod") Long id) {
    return boardService.getOne(id);
  }

  @GetMapping
  public Page<BoardResponse> getAll(@PageableDefault Pageable pageable) {
    return boardService.getAll(pageable);
  }

}
