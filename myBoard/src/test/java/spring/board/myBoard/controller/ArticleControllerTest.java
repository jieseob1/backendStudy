package spring.board.myBoard.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("View 컨트롤러 - 게시글")
@WebMvcTest(ArticleController.class)
class ArticleControllerTest {

    private final MockMvc mvc;

    public ArticleControllerTest(@Autowired MockMvc mvc) { //자동으로 연결
        //autowired 생략 불가 => 테스트 패키지에 있을 때 생성자가 하나만 있을 때는 생략할 수 없다. 원래 스펙상 가능한데, 테스트 패키지는 안된다.
        this.mvc = mvc;
    }
    @DisplayName("[vew][GET] 게시글 리스트 (게시판) 페이지 - 정상 호출")
    @Test
    public void givenNothing_whenRequestingArticlesView_thenReturnArticlesView() throws Exception {
        //given

        //when
        mvc.perform(get("/articles")) //아티클 정보들 보여준다.
            .andExpect(status().isOk()) //status 체크 ctrl + space
            .andExpect(content().contentType(MediaType.TEXT_HTML)) //컨텐츠 내용 => view니까 text_html
            .andExpect(model().attributeExists("articles")); //구현까지 생각한거 게시글들의 목록이 보여야함 => view에다가 모델 attribute로 데이터를 밀어 넣어줌
        //모델 attribute라는 이름의 맵에 이 이름의 키가 있는지 데이터가 있는지 그거까지만 검사한다.

        //then
    }

    @DisplayName("[vew][GET] 게시글 리스트 (게시글) 페이지 - 정상 호출") //단건 조회
    @Test
    public void givenNothing_whenRequestingArticleView_thenReturnArticleView() throws Exception {
        //given

        //when
        mvc.perform(get("/articles/1")) //아티클 정보들 보여준다.
            .andExpect(status().isOk()) //status 체크 ctrl + space
            .andExpect(content().contentType(MediaType.TEXT_HTML))
            .andExpect(model().attributeExists("article"));

        //then
    }

    @DisplayName("[vew][GET] 게시글 검색 전용 페이지 - 정상 호출") //단건 조회
    @Test
    public void givenNothing_whenRequestingArticleSearchView_thenReturnArticleSearchView() throws Exception {
        //given

        //when
        mvc.perform(get("/articles/search")) //아티클 정보들 보여준다.
            .andExpect(status().isOk()) //status 체크 ctrl + space
            .andExpect(content().contentType(MediaType.TEXT_HTML));

        //then
    }

    //게시판 페이지에서 밑에다가 검색 바 넣을 거라서 아래꺼 필요 없을 수 있음 => 스펙에 넣고 만들어는 보자 => 검색을 전용으로 해주는 페이지 하나, 일반적인 게시판 페이지에서도 검색 바 줘서 검색 기능 할 수 있께

    @DisplayName("[vew][GET] 게시글 검색 전용 페이지 - 정상 호출") //단건 조회
    @Test
    public void givenNothing_whenRequestingArticleHashTagSearchView_thenReturnArticleHashTagSearchView() throws Exception {
        //given

        //when
        mvc.perform(get("/articles/hash-tag")) //아티클 정보들 보여준다.
            .andExpect(status().isOk()) //status 체크 ctrl + space
            .andExpect(content().contentType(MediaType.TEXT_HTML));

        //then
    }
}