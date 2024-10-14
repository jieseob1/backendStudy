package spring.board.myBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import spring.board.myBoard.domain.Article;

@RepositoryRestResource // detection-strategy에서 annotated와 연관
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>
{ // DB에 접근하기 위한 인터페이스 설정

}
