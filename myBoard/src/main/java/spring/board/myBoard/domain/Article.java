package spring.board.myBoard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Objects;

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
public class Article {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 auto implement(?)
    private Long id;
    //setter를 필드에 저으이 => id에 정의하고 싶지 않아서
//notnull 필드 => nullable = true가 기본
    @Setter @Column(nullable = false) private String title; //제목
    @Setter @Column(nullable = false, length = 10000) private String content;

    //null 필드
    @Setter private String hashTag;

    // jpa auditing
    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt;
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy; // 누가 만들었는지에 대한 정보
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt;
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;

    //엔티티 기본 기능


    protected Article() { // 밖에서 new로 생성 x
    }

    //팩토리 메서드를 통해 제공하도록
    public Article(String title, String content, String hashTag) { //도메인과 관련있는 정보만
        this.title = title;
        this.content = content;
        this.hashTag = hashTag;
    }
    //의도를 전달한다.
    public static Article of (String title, String content, String hashTag) { //도메인과 관련있는 정보만
        return new Article(title,content,hashTag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false; // pattern variable 적용
        return id  != null && id.equals(article.id); // id가 nonull이기 때문에 equals를 사용한다. => db를 아직 안 만든 경우는 null로 빠지게 하기
        //모두 다르게 보겠다
        //id  != null => id가 부여되지 않았다 즉, 영속화 되지 않았다의 경우 => 동등성 검사 의미 없는것으로 봄 => 모두 다른것으로 바라보겠다. false 처리 한다

    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
