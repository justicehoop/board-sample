package com.naver.jpa.board.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.naver.jpa.board.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Member findByUserId(String userId);

  Member findByNickname(String nickname);
}
