package com.naver.jpa.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.naver.jpa.board.repository.ArticleRepository;
import com.naver.jpa.board.repository.CommentRepository;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentService {
}
