package com.Test.test.controller;

import com.Test.test.entity.Board;
import com.Test.test.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class TestController {
    @Autowired
    private final BoardService boardService;

    public TestController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/test/write")
    public String boardWriteForm(){
        return "boardwrite";
    }

    @PostMapping("/test/write1")
    public String boardWriteForm(Board board){
        boardService.write(board);
        return "";
    }

    //개인학습

    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name", "내이름");
        model.addAttribute("img", "image/hadoop-cycle.png");
        return "hello";
    }

    @RestController
    @RequestMapping("/api")
    public class ApiController {

        @GetMapping("/name")
        public String name() {
            return "내이름";
        }
    }




}
