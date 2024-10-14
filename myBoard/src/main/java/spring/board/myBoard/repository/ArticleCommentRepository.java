package spring.board.myBoard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import spring.board.myBoard.domain.ArticleComment;

//여기서는 Repository 어노테이션 사용하지 않는다. 이미 JpaRepo에 존재함
@RepositoryRestResource
public interface ArticleCommentRepository extends JpaRepository<ArticleComment,Long> { //jpaRepo를 확장한 ArticleCommentRepo 인터페이스

}
