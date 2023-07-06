package com.naver.jpa.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import com.naver.jpa.board.dto.MemberEditRequest;
import com.naver.jpa.board.dto.MemberJoinRequest;
import com.naver.jpa.board.dto.MemberResponse;
import com.naver.jpa.board.service.MemberService;

@RequestMapping("/v1/users")
@RequiredArgsConstructor
@RestController
public class MemberController {
  private final MemberService memberService;

  @PostMapping
  public MemberResponse join(@RequestBody MemberJoinRequest memberJoinRequest) {
    return memberService.join(memberJoinRequest);
  }

  @PutMapping("/{id}")
  public MemberResponse edit(@PathVariable("id") Long id, MemberEditRequest memberEditRequest) {
    return memberService.edit(id, memberEditRequest);
  }

  @GetMapping("/{id}")
  public MemberResponse getOne(Long id) {
    return memberService.getOne(id);
  }

  @GetMapping
  public Page<MemberResponse> getAll(Pageable pageable) {
    return memberService.getAll(pageable);
  }
}
