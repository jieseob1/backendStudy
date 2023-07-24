package spring.board.myBoard.repository;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import spring.board.myBoard.config.JPAConfig;
import spring.board.myBoard.domain.Article;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JPAConfig.class) // auditing이 안켜짐
@DataJpaTest //jpa에 관련된 test를 위한 어노테이션
class JpaRepositoryTest {
    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository, //생성자 주입
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {

        //given

        //when
        List<Article> articles = articleRepository.findAll(); // findAll은 List 기능

        assertThat(articles)
                .isNotNull()
                .hasSize(0);
        //then
    }

    @DisplayName("insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() { //나중에 만들기

        //given
        long previousCount = articleRepository.count();
        //when
//        Article savedArticle = articleRepository.save(Article.of())
        //then
    }
}
