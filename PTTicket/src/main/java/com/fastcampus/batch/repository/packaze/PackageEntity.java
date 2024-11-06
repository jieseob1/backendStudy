package com.fastcampus.batch.repository.packaze;

import com.fastcampus.batch.repository.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "package")
public class PackageEntity extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성 전략을 설정하는 어노테이션 =>pk 생성을 위해 db에 위임
  private Long packageSeq;

  private String packageName;
  private Integer count;
  private Integer period;
}
