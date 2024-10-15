package spring.board.myBoard.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import spring.board.myBoard.domain.ArticleComment;
import spring.board.myBoard.domain.QArticleComment;

//여기서는 Repository 어노테이션 사용하지 않는다. 이미 JpaRepo에 존재함
@RepositoryRestResource
public interface ArticleCommentRepository extends
    JpaRepository<ArticleComment, Long>,
    QuerydslPredicateExecutor<ArticleComment>,
    QuerydslBinderCustomizer<QArticleComment> { //jpaRepo를 확장한 ArticleCommentRepo 인터페이스

  @Override
  default void customize(QuerydslBindings bindings, QArticleComment root) {
    bindings.excludeUnlistedProperties(true); // 리스팅 되지 않는 프로퍼티는 제거
    bindings.including(root.content, root.createdAt, root.createdBy); //원하는 필드 추가 현재 해당 정보만 보여주도록 하자
    //현재는 exact match로 동작하는 데 룰을 바꾸겠다.
//    bindings.bind(root.title).first((StringExpression::like)); // like `${v}` like에서는 %를 넣는것을 내가 직접 해줘야 함
    bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // ((path, value) -> path.eq(value)) like `%${v}%`
    bindings.bind(root.createdAt).first(DateTimeExpression::eq);
    bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
  }
}
