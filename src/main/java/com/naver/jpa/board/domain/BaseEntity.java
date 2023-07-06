package com.naver.jpa.board.domain;

import java.time.LocalDateTime;

import lombok.Getter;

//@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity<ID>  implements IdHandler<ID> {
  protected Member createdBy;
  protected LocalDateTime createdDateTime;
  protected Member lastModifiedBy;
  protected LocalDateTime lastModifiedDateTime;
}
