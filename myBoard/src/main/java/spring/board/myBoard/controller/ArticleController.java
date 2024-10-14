package spring.board.myBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/articles")
@Controller
public class ArticleController {

  @GetMapping
  public String articles(ModelMap map) {
    map.addAttribute("articles", List.of()); //여기서 article에 대한 정보를 넣어서 보여주는 듯
    return "articles/index.html";
  }
}
