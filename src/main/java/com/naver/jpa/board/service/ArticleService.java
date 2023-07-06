package com.naver.jpa.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.naver.jpa.board.domain.Article;
import com.naver.jpa.board.domain.Board;
import com.naver.jpa.board.dto.ArticleRequest;
import com.naver.jpa.board.dto.ArticleResponse;
import com.naver.jpa.board.exception.ResourceNotFoundException;
import com.naver.jpa.board.repository.ArticleRepository;
import com.naver.jpa.board.repository.BoardRepository;

@Service
@RequiredArgsConstructor
public class ArticleService {

  private final ArticleRepository articleRepository;
  private final BoardRepository boardRepository;

  public ArticleResponse create(Long boardId, ArticleRequest articleRequest) {
    Board board = findBoardById(boardId);
    Article article = Article.of(board, articleRequest.getTitle(), articleRequest.getContent());
    return ArticleResponse.fromArticle(articleRepository.save(article));
  }

  public Page<ArticleResponse> getAll(Pageable pageable) {
    List<ArticleResponse> articles = articleRepository.findAll(pageable).stream()
      .map(article -> ArticleResponse.fromArticle(article))
      .collect(Collectors.toList());
    return new PageImpl<>(articles, pageable, articles.size());
  }

  private Board findBoardById(Long id) {
    return boardRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(String.format("board(id:%d) does not exist", id)));

  }


}
