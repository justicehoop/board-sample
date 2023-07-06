package com.naver.jpa.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.jpa.board.domain.Article;
import com.naver.jpa.board.domain.Board;

public interface ArticleRepository extends JpaRepository<Article, Long> {

  void deleteByBoard(Board board);

}
