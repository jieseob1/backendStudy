package com.fastcampus.batch.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 기능 활성화 -> auditing이란 엔티티 객체의 생성, 수정 시간을 자동으로 기록하는 기능
//jpa 사용해서 엔티티를 테이블에 맵핑할 때 공통적으로 가지고 있는 필드 컬럼들이 있다 => 이런 필드들을 자동으로 관리하는 기능
@Configuration // 스프링 설정 클래스로 지정
public class JpaConfig {
}
