package spring.board.myBoard.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString // 쉽게 출력
@Table(indexes = { // index를 잡을 수 있다. title,content, createdAt,By로 검색할 수 있게 만들거다 => content는 사이즈가 큼 인덱스로 안 걸꺼임 => hashtag
        @Index(columnList = "content"), //이렇게 만듬으로써 조회 쿼리가 더 빨라진다. title이나 hashtag에 따라 데이터를 조회하는 경우가 많다면, 이런 인덱스를 만들면 성능이 크게 향상될 수 있다.
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})

@EntityListeners(AuditingEntityListener.class)
@Entity // 엔티티임을 알려줌
public class ArticleComment {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 auto implement(?)
    private Long id;

    @Setter @ManyToOne(optional = false) private Article article; //게시글 (ID) 객체지향적으로 연관관계 부여함 => 필수값
    @Setter @Column(nullable = false,length = 500) private String content; // 본문

    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt;
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; // 누가 만들었는지에 대한 정보
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt;
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;

    protected ArticleComment() { // 기본 생성자
    }
    //1. 객체 초기화: 초기상태 설정
    //2. 객체 생성 용이성
    //3. 프레임워크 요구사항
    //4. 상속과 다형ㅇ성

    private ArticleComment (Article article, String content) {
        this.article = article;
        this.content = content;

    }

    //private 접근 팩토리 메서드
    public static ArticleComment of(Article article, String content) {
        return new ArticleComment(article,content);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if(!(o instanceof ArticleComment that)) return false;
        return id  != null && id.equals(that.id); // id가 nonull이기 때문에 equals를 사용한다. => db를 아직 안 만든 경우는 null로 빠지게 하기
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
