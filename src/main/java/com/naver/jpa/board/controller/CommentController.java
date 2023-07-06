package com.naver.jpa.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequestMapping("/v1/boards/{boardId}/articles/{articleId}/comments")
@RequiredArgsConstructor
@RestController
public class CommentController {
}
