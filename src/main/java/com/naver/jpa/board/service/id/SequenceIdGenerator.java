package com.naver.jpa.board.service.id;

import java.util.concurrent.atomic.AtomicLong;

public class SequenceIdGenerator implements IdGenerator<Long> {
  private final AtomicLong id = new AtomicLong(1L);

  @Override
  public Long generate() {
    return id.incrementAndGet();
  }
}
