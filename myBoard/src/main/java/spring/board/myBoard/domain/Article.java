package spring.board.myBoard.domain;

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
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString // 쉽게 출력
@Table(indexes = { // index를 잡을 수 있다. title,content, createdAt,By로 검색할 수 있게 만들거다 => content(mysql은 풀텍스트 서치 지원)는 사이즈가 큼 인덱스로 안 걸꺼임 => hashtag
    @Index(columnList = "title"), //이렇게 만듬으로써 조회 쿼리가 더 빨라진다. title이나 hashtag에 따라 데이터를 조회하는 경우가 많다면, 이런 인덱스를 만들면 성능이 크게 향상될 수 있다.
    @Index(columnList = "hashtag"),
    @Index(columnList = "createdAt"),
    @Index(columnList = "createdBy"),
})
@Entity // 엔티티임을 알려줌
public class Article extends AuditingFields {

  @Id //primary key
  @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 auto increment => mysql의 auto increment는 Identity로 만들어짐
  private Long id;
  //setter를 필드에 정의 안함 => id에 정의하고 싶지 않아서
//notnull 필드 => nullable = true가 기본
  @Setter
  @Column(nullable = false)
  private String title; //제목
  @Setter
  @Column(nullable = false, length = 10000)
  private String content;

  //null 필드
  @Setter
  private String hashTag;


  //양방향 바인딩 => one toMany

  //원치않게 사라질수 있음 => cascade
  //Tostring.Exclude를 안하면 circular referencing 문제가 생길 수 있다.

  @ToString.Exclude // 이부분에서 articlecomment를 뽑는건 비정상 //circular referencing=> ArticleComment들어가서 내용 또 찍는데, article이 또 있어서 circular referencing존재 가능
  @OrderBy("id") //정렬할것임
  @OneToMany(mappedBy = "article", cascade = CascadeType.ALL) //article테이블로부터 온것이다. => cascading으로 강하게 연결되어있어서 실무에서는 mappedby안하는 경우도 있다. 운영에서느 백업 목적으로 지우고 싶지 않을 수도 있다.
  private final Set<ArticleComment> articleComments = new LinkedHashSet<>(); // 중복 허용x, collection으로 보겠다.한번만 세팅 할거므로 final 기본적으로 set 쓴다고 하심


  // jpa auditing
//  @CreatedDate
//  @Column(nullable = false)
//  private LocalDateTime createdAt; //어노테이션으로 자동 auditing
//  @CreatedBy
//  @Column(nullable = false, length = 100)
//  private String createdBy; // 누가 만들었는지에 대한 정보는 jpaconfig에서
//  @LastModifiedDate
//  @Column(nullable = false)
//  private LocalDateTime modifiedAt;
//  @LastModifiedBy
//  @Column(nullable = false, length = 100)
//  private String modifiedBy;

  //엔티티 기본 기능

//  @Embedded AAA aa; // 묶은 클래스를 하나 만들어서 안에다가 필드를 추가하는 방법
//  class AAA {
//    //위에 4가지 정보 추가
//}

  protected Article() { // Hibernate사용시 기본 생성자 => 오픈하지 않을거임 따라서 protected
  }

  //팩토리 메서드를 통해 제공하도록 => new 쓰지 않고 사용 할 수 있도록 의도 전달하기 위해서
  private Article(String title, String content, String hashTag) { //도메인과 관련있는 정보만
    this.title = title;
    this.content = content;
    this.hashTag = hashTag;
  }

  //의도를 전달한다.
  public static Article of(String title, String content, String hashTag) { //도메인과 관련있는 정보만
    return new Article(title, content, hashTag);
  } //팩토리 메서드 패턴

//    @Override
//    public boolean equals(Object o) { // 동일성 동등성 검사 => List에 담을 때 중복 검사 하도록 => entity에서는 entitiyAndHashcode롬복 안쓸거임 전부 다 필드 비교 안하도록
//        if (this == o) return true;
//        if (!(o instanceof Article article)) return false; // pattern variable 적용
//        return id  != null && id.equals(article.id); // id가 nonull이기 때문에 equals를 사용한다. => db를 아직 안 만든 경우는 null로 빠지게 하기
//        //모두 다르게 보겠다
//        //id  != null => id가 부여되지 않았다 즉, 영속화 되지 않았다의 경우 => 동등성 검사 의미 없는것으로 봄 => 모두 다른것으로 바라보겠다. false 처리 한다
//
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id);
//    }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
    if (!(o instanceof Article article)) {
      return false; //pattern matching 사용
    }
//        Article article = (Article) o; // pattern variable => pattern matching 확인
    return id != null && id.equals(article.id); // 데이터베이스에 연결하지 않았을 때는 아이디 부여 안해서 해당 부분에 관련된 체크 추가
  } //아이디 부여 없으면 동등성 검사 자체가 의미 없다고 판단

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
