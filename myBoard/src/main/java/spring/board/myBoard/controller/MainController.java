package spring.board.myBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // Declare Controller
public class MainController {

    @GetMapping("/")
    public String root() {
        return "forward:/articles";
    }
    //return logic view

}
