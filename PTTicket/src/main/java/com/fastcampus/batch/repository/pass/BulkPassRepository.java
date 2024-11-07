package com.fastcampus.batch.repository.pass;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BulkPassRepository extends JpaRepository<BulkPassEntity, Integer> {
  List<BulkPassEntity> findByStatusAndStartedAtGreaterThan(BulkPassStatus status, LocalDateTime startedAt);
  //메소드 규칙 이름 => Method Name Resolution
  // 메소드 이름 분석, 자동 쿼리 생성
  // 1. 메소드 이름 파싱 과정
  /**
   * - findBy: 조회 쿼리임을 나ㄴ냄
   * - 엔티티의 status 필드를 검색
  * And: 조건 결합
   * StartedAtGreaterThan: startedAt 필드가 주어진 값보다 큰 데이터
   * 실제 변환 되는 쿼리
   * SELECT * FROM bulk_pass WHERE status = ? AND started_at > ? => 여기서의 ?는 메소드 인자로 전달된 값으로 치환됨 => ?로 하는 이유는 메소드 인자로 전달된 값이 무엇인지 모르기 때문
   * 
   * 동작 방식
   * 
   * 
   * 동작 방식:
    * 스프링 애플리케이션 시작 시 BulkPassRepository 인터페이스를 스캔 => Component Scan
    * 메소드 이름을 파싱하여 쿼리 생성 규칙에 따라 실제 쿼리 생성 => Method Name Resolution
    * 프록시 객체를 통해 실제 구현체 생성 => 프록시 객체란 실제 엔티티 객체를 대신해서 데이터베이스와 상호작용하는 객체
    * 관련 정보를 찾아볼 수 있는 공식 문서와 사이트들:
    * 공식 문서:
      * Spring Data JPA 공식 문서: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
      * 쿼리 메소드 키워드 레퍼런스: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repository-query-keywords
    * 주요 키워드 예시:
      * And, Or
      * GreaterThan, LessThan
      * Between
      * Like, NotLike
      * StartingWith, EndingWith
      * OrderBy

      이러한 기능은 Spring Data JPA의 QueryExecutorMethodInterceptor와 JpaQueryLookupStrategy 클래스들이 핵심적인 역할을 담당한다.
   */
}
