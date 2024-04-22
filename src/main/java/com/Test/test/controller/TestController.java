package com.Test.test.controller;

import com.Test.test.entity.Board;
import com.Test.test.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class TestController {
    @Autowired
    private final BoardService boardService;

    public TestController(BoardService boardService) {
        this.boardService = boardService;
    }

    //글쓰기
    @GetMapping("/test/write")
    public String boardWriteForm(){
        return "boardwrite";
    }


    @PostMapping("/test/write1")
    public String boardWriteForm(Board board, Model model){
        boardService.write(board);
        model.addAttribute("message" , "글 작성이 완료되었습니다");
        model.addAttribute("searchUrl", "/test/list");
        model.addAttribute("board", boardService.boardList());
        return "boardlist";
    }


    @GetMapping("/test/view")
    public String boardView(@RequestParam(name="id") Integer id, Model model){
        model.addAttribute("board",boardService.boardview(id));
        return "boardview";
    }

    //삭제하기
    @GetMapping("/test/delete")
    public String boardDelete(@RequestParam(name="id") Integer id, Model model){
        boardService.delete(id);
        return "boarddelete";
    }




}
