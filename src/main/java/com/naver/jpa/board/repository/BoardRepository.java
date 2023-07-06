package com.naver.jpa.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.jpa.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {
  Board findByName(String name);
}
