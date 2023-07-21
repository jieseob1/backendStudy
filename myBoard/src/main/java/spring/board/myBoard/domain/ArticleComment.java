package spring.board.myBoard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@ToString // 쉽게 출력
@Table(indexes = { // index를 잡을 수 있다. title,content, createdAt,By로 검색할 수 있게 만들거다 => content는 사이즈가 큼 인덱스로 안 걸꺼임 => hashtag
        @Index(columnList = "title"), //이렇게 만듬으로써 조회 쿼리가 더 빨라진다. title이나 hashtag에 따라 데이터를 조회하는 경우가 많다면, 이런 인덱스를 만들면 성능이 크게 향상될 수 있다.
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})

@EntityListeners(AuditingEntityListener.class)
@Entity // 엔티티임을 알려줌
public class ArticleComment {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 auto implement(?)
    private Long id;

    private @ManyToOne(optional = false) Article article; //게시글 (ID) 객체지향적으로 연관관계 부여함 => 필수값
    private String content; // 본문

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}
