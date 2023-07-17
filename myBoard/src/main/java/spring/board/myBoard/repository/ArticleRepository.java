package spring.board.myBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.board.myBoard.domain.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> { // DB에 접근하기 위한 인터페이스 설정

}
