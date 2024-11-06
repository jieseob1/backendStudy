package com.fastcampus.batch.repository.pass;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface PassRepository extends JpaRepository<PassEntity, Integer> {
  @Transactional // 여기에 transactional 어노테이션을 추가하면 메서드 내에서 수행되는 모든 작업이 하나의 트랜잭션으로 처리됨
  @Modifying // 여기에 modifying 어노테이션을 추가하면 쿼리가 수정 쿼리임을 명시
  @Query(value = "UPDATE PassEntity p" +
      "          SET p.remainingCount = :remainingCount," +
      "              p.modifiedAt = CURRENT_TIMESTAMP" +
      "        WHERE p.passSeq = :passSeq") //해당 쿼리는 업데이트 쿼리임을 명시
  int updateRemainingCount(Integer passSeq, Integer remainingCount);
}
