package spring.board.myBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.board.myBoard.domain.ArticleComment;

public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long> { //jpaRepo를 확장한 ArticleCommentRepo 인터페이스

}
