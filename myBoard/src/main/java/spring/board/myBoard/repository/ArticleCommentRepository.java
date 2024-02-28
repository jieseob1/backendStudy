package spring.board.myBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import spring.board.myBoard.domain.ArticleComment;

@RepositoryRestResource
public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long> { //jpaRepo를 확장한 ArticleCommentRepo 인터페이스

}
