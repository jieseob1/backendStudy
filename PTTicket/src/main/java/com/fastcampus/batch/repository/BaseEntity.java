package com.fastcampus.batch.repository;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

//이같은 설정들을 enable 해주는게 jpaconfig에 있는 @EnableJpaAuditing 어노테이션 덕분임 
@MappedSuperclass // 엔티티 클래스들이 공통적으로 가지고 있는 필드들을 정의하는 클래스 => 이걸로 인해 createdAt, modifiedAt 필드들이 implement 하는 모든 엔티티 클래스에 공통적으로 존재하게 됨
@EntityListeners(AuditingEntityListener.class) // Auditing 기능을 활성화하는 어노테이션
public abstract class BaseEntity {
  @CreatedDate // 엔티티 객체가 생성된 시간을 자동으로 기록하는 어노테이션
  @Column(updatable = false, nullable = false) // 엔티티 객체가 생성된 시간을 자동으로 기록하는 어노테이션 createdAt 필드는 업데이트 불가능하고 반드시 값이 있어야 함
  private LocalDateTime createdAt;

  @LastModifiedDate // 엔티티 객체가 수정된 시간을 자동으로 기록하는 어노테이션
  private LocalDateTime modifiedAt;
}
