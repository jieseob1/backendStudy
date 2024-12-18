package com.fastcampus.batch.repository.pass;

import com.fastcampus.batch.repository.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name = "pass")
public class PassEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 DB에 위임합니다. (AUTO_INCREMENT)
  private Integer passSeq;
  private Integer packageSeq;
  private String userId;

  @Enumerated(EnumType.STRING)
  private PassStatus status;
  private Integer remainingCount;

  private LocalDateTime startedAt;
  private LocalDateTime endedAt;
  private LocalDateTime expiredAt;
}