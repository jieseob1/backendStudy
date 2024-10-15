package spring.board.myBoard.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest => slice 테스트 => 컨트롤러 외의 빈들을 로드하지 않는다. 불필요한건 로드 안함
@DisplayName("Data Rest - API 테스트")
@Transactional // db에 관련되어 있는 정보이므로 기본동작은 rollback 각각은 transaction하게 묶인다. => DB 접근이 일어나므로 안전을 위해 적용함 테스트 안에서는 롤백 정책으로 동작한다.
@AutoConfigureMockMvc //MockMvc의 존재를 알수 없음 따라서 추가해줌
@SpringBootTest // 따라서 위의 webmvcTest를 안쓰고 통합테스트를 쓴다. =>SpringBootTest는 통합 테스트 내부적으로 environment에 대한 에노테이션도 넣을 수 있음 default는 mock임
public class DataRestTest {
//DataRestTest는 Integration Test로 접근하는 수 밖에 없다. => 모킹도 안되고 함부로 모킹을 하면 테스트가 제대로 동작하지 않게 된다.
  private final MockMvc mvc;

  public DataRestTest(@Autowired MockMvc mvc) {
    this.mvc = mvc;
  }

  @DisplayName("[api] 게시글 리스트 조회")
  @Test
  void givenNothing_whenRequestingArticles_thenReturnsArticlesJsonResponse() throws Exception {
    //given

    //when
    mvc.perform(get("/api/articles")) //리스트 조회
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.valueOf("application/hal+json"))); // data rest의 미디어 타입은 hal+json으로 나온다. 따라서 우리가 따로 추가를 해줘야 한다.
  }
//  @DisplayName("[api] 게시글 단건 조회") //아무 정보 없으니까 그냥 주석으로 변경함
//  @Test
//  void givenNothing_whenRequestingArticle_thenReturnsArticleJsonResponse() throws Exception {
//    //given
//
//    //when
//    mvc.perform(get("/api/articles/1")) //리스트 조회 테스트 아이디가 있다고 가정하고 이렇게 만들어줌
//        .andExpect(status().isOk())
//        .andExpect(content().contentType(MediaType.valueOf("application/hal+json"))); // data rest의 미디어 타입은 hal+json으로 나온다. 따라서 우리가 따로 추가를 해줘야 한다.
//  }

}