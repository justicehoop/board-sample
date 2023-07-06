package com.naver.jpa.board.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import com.naver.jpa.board.domain.Board;
import com.naver.jpa.board.service.id.IdGenerator;
import com.naver.jpa.board.service.id.SequenceIdGenerator;
import com.naver.jpa.board.dto.BoardRequest;
import com.naver.jpa.board.dto.BoardResponse;
import com.naver.jpa.board.exception.ResourceNotFoundException;
import com.naver.jpa.board.repository.BoardRepository;

@RequiredArgsConstructor
@Service
public class BoardService {

  private final BoardRepository boardRepository;

  private final BoardValidator boardValidator;

  public BoardResponse create(BoardRequest boardRequest) {
    Board board = Board.of(boardRequest.getName(), boardRequest.getDescription(), boardRequest.getBoardType());
    boardValidator.validate(board);
    return BoardResponse.from(boardRepository.save(board));
  }

  public BoardResponse edit(Long id, BoardRequest boardRequest) {
    Board savedBoard = findOne(id);
    boardRepository.save(savedBoard.edit(boardRequest.getName(), boardRequest.getDescription()));
    return BoardResponse.from(savedBoard);
  }

  public BoardResponse getOne(Long id) {
    return BoardResponse.from(findOne(id));
  }


  public void delete(Long id) {
    Board board = findOne(id);
    boardRepository.delete(board);
  }

  private Board findOne(Long id) {
    return boardRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(String.format("the board(id:%d) does not exist", id)));
  }

  public Page<BoardResponse> getAll(Pageable pageable) {
    Page<Board> all = boardRepository.findAll(pageable);
    return new PageImpl<>(all.getContent().stream()
      .map(board-> BoardResponse.from(board)).collect(Collectors.toList())
      ,pageable, all.getTotalElements());
  }

  @Component
  @RequiredArgsConstructor
  public static class BoardValidator {
    private final BoardRepository boardRepository;

    public void validate(Board board) {
      validateUniqueName(board);
    }

    private void validateUniqueName(Board board) {
      Board byName = boardRepository.findByName(board.getName());

      if (byName != null && !byName.equals(board)) {
        throw new IllegalStateException(String.format("board(name:%s) already exist", board.getName()));
      }
    }
  }

}
