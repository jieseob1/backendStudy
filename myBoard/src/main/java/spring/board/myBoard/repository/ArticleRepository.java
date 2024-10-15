package spring.board.myBoard.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import spring.board.myBoard.domain.Article;
import spring.board.myBoard.domain.QArticle;

@RepositoryRestResource // detection-strategy에서 annotated와 연관
public interface ArticleRepository extends
    JpaRepository<Article, Long>,
    QuerydslPredicateExecutor<Article>, // 일반적인 엔티티 추가 => 현재 모든 필드들이 열려있는 상태
    QuerydslBinderCustomizer<QArticle> { // DB에 접근하기 위한 인터페이스 설정
  /*
  1. QuerydslPredicateExecutor => 해당 엔티티 안에 있는 모든 필드에 대한 기본 검색 기능 추가 => 이거 하나만 넣어도 검색 기능은 끝난다.
  2. QuerydslBinderCustomizer => 추가적으로 우리가 입맛에 맞는 검색 기능을 만들기 위함
   */

  @Override
  default void customize(QuerydslBindings bindings, QArticle root) // 검색에 대한 세부 규칙이 재구성된다.
  {
    bindings.excludeUnlistedProperties(true); // 리스팅 되지 않는 프로퍼티는 제거
    bindings.including(root.title, root.content, root.hashTag, root.createdAt, root.createdBy); //원하는 필드 추가 현재 해당 정보만 보여주도록 하자
    //현재는 exact match로 동작하는 데 룰을 바꾸겠다.
//    bindings.bind(root.title).first((StringExpression::like)); // like `${v}` like에서는 %를 넣는것을 내가 직접 해줘야 함
    bindings.bind(root.title).first(StringExpression::containsIgnoreCase); // ((path, value) -> path.eq(value)) like `%${v}%`
    bindings.bind(root.content).first(StringExpression::containsIgnoreCase); // ((path, value) -> path.eq(value)) like `%${v}%`
    bindings.bind(root.hashTag).first(StringExpression::containsIgnoreCase);
    bindings.bind(root.createdAt).first(DateTimeExpression::eq);
    bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
  }
  //원래는 인터페이스라 구현이 불가능했지만 앞에 java 8 이후로 default를 넣으면 가능해짐
  //우리는 spring data rest를 이용해서 기본 기능들은 다 구현하고 있으므로 현재 인터페이스만 가지고 모두 다 사용하게끔 접근하고 있음 따라서 여기서 그냥 default로 해서 구현체를 만들어서 사용해도 괜찮다는 판단임
}
