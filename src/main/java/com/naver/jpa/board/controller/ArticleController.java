package com.naver.jpa.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.naver.jpa.board.dto.ArticleRequest;
import com.naver.jpa.board.dto.ArticleResponse;
import com.naver.jpa.board.service.ArticleService;

@RequestMapping("/v1/boards/{boardId}/articles")
@RequiredArgsConstructor
@RestController
public class ArticleController {
  private final ArticleService articleService;

  @PostMapping
  public ArticleResponse create(@PathVariable("boardId") Long boardId, @RequestBody ArticleRequest articleRequest) {
    return articleService.create(boardId, articleRequest);
  }

  @GetMapping
  public Page<ArticleResponse> getAll(@PageableDefault Pageable pageable) {
    return articleService.getAll(pageable);
  }
}
