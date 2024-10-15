//package spring.board.myBoard.repository;
//
//
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.ActiveProfiles;
//import spring.board.myBoard.config.JPAConfig;
//import spring.board.myBoard.domain.Article;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.*;
//
//@ActiveProfiles("testdb")
////위에 부분 application.yaml 파일과 관련 있음
////@AutoConfigureTestDatabase(replace = Replace.NONE) //테스트 상태에서 돌린다고 해도 따로 테스트 db를 불러오지 않고 설정되어 있는것을 쓴다.실제 db를 테스트 돌릴때 사용
//@DisplayName("JPA 연결 테스트")
//@Import(JPAConfig.class) // auditing이 안켜짐 => 해당 부분이 안들어감
//@DataJpaTest //jpa에 관련된 test를 위한 어노테이션 메소드 단위로 트랜잭션 걸리도록 함 => rollback으로 하게 됨
//class JpaRepositoryTest {
//  private final ArticleRepository articleRepository;
//  private final ArticleCommentRepository articleCommentRepository;
//
//  public JpaRepositoryTest(
//      ArticleRepository articleRepository, //생성자 주입
//      ArticleCommentRepository articleCommentRepository
//  ) {
//    this.articleRepository = articleRepository;
//    this.articleCommentRepository = articleCommentRepository;
//  }
//
//  @DisplayName("select 테스트")
//  @Test
//  void givenTestData_whenSelecting_thenWorksFine() {
//
//    //given
//
//    //when
//    List<Article> articles = articleRepository.findAll(); // findAll은 List 기능
//
//    assertThat(articles)
//        .isNotNull()
//        .hasSize(0);
//    //then
//  }
//
//  @DisplayName("insert 테스트")
//  @Test
//  void givenTestData_whenInserting_thenWorksFine() { //나중에 만들기
//
//    //given
//    long previousCount = articleRepository.count();
//
//    //when
//    articleRepository.save(Article.of("new article", "new Content", "new HashTag"));
//
//    //then
//    assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
//  }
//
//  @DisplayName("update 테스트")
//  @Test
//  void givenTestData_whenUpdating_thenWorksFine() { //나중에 만들기
//
//    //테스트시에는 기본적으로 rollback으로 동작한다.
//    //given
//    Article article = articleRepository.findById(1L).orElseThrow();
//    String updatedHashtag = "#springboot";
//    article.setHashTag(updatedHashtag); //entity에 있는 setter사용
//
//    //when
//    Article savedArticle = articleRepository.saveAndFlush(article);
//    //변경점이 rollback에 의해서 중요하지 않다라고 하면 생략될 수 있다 그중 하나가 update인데, 그냥 save만 하고 끝내버리면 rollback할거라 변경된게 없다
//    // 따라서 save 하고 flush를 진행해줘야 한다. => flush를 해줘야지 반영이 된다.
//    //이렇게 하면 hibernate가 쿼리 생성후 db에 반영함
//
//    //then
//    assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag); // savedarticle이
//    //assertj에서 제공하는 기능
//
//  }
//
//  @DisplayName("delete 테스트")
//  @Test
//  void givenTestData_whenDeleting_thenWorksFine() { //나중에 만들기
//
//    //연관관계 => cascading 옵션 사용
//    //테스트시에는 기본적으로 rollback으로 동작한다.
//
//    //given
//    Article article = articleRepository.findById(1L).orElseThrow();
//    long previousArticleCount = articleRepository.count();
//    long previousArticleCommentCount = articleCommentRepository.count(); //우리는 cascading도 확인하고 있어서 해당 부분에 대해서도 확인이 필요하다.
//    int deletedCommentsSize = article.getArticleComments().size();//set
//
//    //when
//    //변경점이 rollback에 의해서 중요하지 않다라고 하면 생략될 수 있다 그중 하나가 update인데, 그냥 save만 하고 끝내버리면 rollback할거라 변경된게 없다
//    // 따라서 save 하고 flush를 진행해줘야 한다.
//    //이렇게 하면 hibernate가 쿼리 생성후 db에 반영함
//
//    articleRepository.delete(article);
//
//    //then
//    assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
//    assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize); //아티클에서 댓글 정보 지운만큼
//
//  }
//
//
//}
