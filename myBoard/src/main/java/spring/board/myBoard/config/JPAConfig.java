package spring.board.myBoard.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@EnableJpaAuditing // 엔티티 생성일시, 수정일시 자동 추적 가능 => auditing 기능 활성화
@Configuration // 이 클래스가 구성 클래스임을 나타낸다. => configuration bean이 된다.
public class JPAConfig {

    @Bean // 빈 지정
    public AuditorAware<String> auditorAware() { // auditoraware: JPAAuditor에서 현재 사용자를 제공하는 데 사용된다.
        // id가 들어간다.
        return () -> Optional.of("victor"); //TODO: 스프링 시큐리티로 인증 기능을 붙이게 될때 수정
//        Optional.of(value)는 주어진 값으로 Optional 객체를 생성하는 정적 팩토리 메서드
        //victor라는 사용자로 고정된 값을 현재 사용자로 설정
    }
}
