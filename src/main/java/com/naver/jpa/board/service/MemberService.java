package com.naver.jpa.board.service;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import com.naver.jpa.board.domain.Member;
import com.naver.jpa.board.service.id.IdGenerator;
import com.naver.jpa.board.service.id.SequenceIdGenerator;
import com.naver.jpa.board.dto.MemberEditRequest;
import com.naver.jpa.board.dto.MemberJoinRequest;
import com.naver.jpa.board.dto.MemberResponse;
import com.naver.jpa.board.exception.ResourceNotFoundException;
import com.naver.jpa.board.repository.MemberRepository;

@Transactional
@Service
@RequiredArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  private final MemberValidator memberValidator;

  public MemberResponse join(MemberJoinRequest memberJoinRequest) {
    memberValidator.validate(memberJoinRequest);
    Member member = Member.of(memberJoinRequest.getUserId(), memberJoinRequest.getNickname(), memberJoinRequest.getName(), memberJoinRequest.getPassword1());
    return MemberResponse.from(memberRepository.save(member));
  }

  public MemberResponse edit(Long id, MemberEditRequest memberEditRequest) {
    Member member = findById(id);
    memberValidator.validate(member, memberEditRequest);
    return MemberResponse.from(memberRepository.save(member.edit(memberEditRequest.getNickname(), member.getName())));
  }

  public MemberResponse getOne(Long id) {
    return MemberResponse.from(findById(id));
  }

  private Member findById(Long id) {
    return memberRepository.findById(id)
      .orElseThrow(() -> new ResourceNotFoundException(""));
  }

  public Page<MemberResponse> getAll(Pageable pageable) {
    Page<Member> users = memberRepository.findAll(pageable);
    return new PageImpl<>(users.stream()
      .map(user -> MemberResponse.from(user))
      .collect(Collectors.toList()), pageable, users.getTotalElements()
    );
  }

  @RequiredArgsConstructor
  @Component
  public static class MemberValidator {

    private final MemberRepository memberRepository;

    public void validate(MemberJoinRequest memberJoinRequest) {

      if (memberRepository.findByUserId(memberJoinRequest.getUserId()) != null) {
        throw new IllegalArgumentException("userId already exists");
      }

      validateNickname(null, memberJoinRequest.getNickname());

    }

    public void validate(Member member, MemberEditRequest memberEditRequest) {
      validateNickname(member, memberEditRequest.getNickname());
    }

    private void validateNickname(Member member, String nickname) {
      Member byNickName = memberRepository.findByNickname(nickname);

      if (!byNickName.equals(member)) {
        throw new IllegalArgumentException(String.format("nickname(%s) already exists!"));
      }
    }

  }

}
