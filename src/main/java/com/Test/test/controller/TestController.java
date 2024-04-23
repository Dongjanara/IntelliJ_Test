package com.Test.test.controller;

import com.Test.test.entity.Board;
import com.Test.test.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;


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

    //직접적인 글 쓰는 폼
    @PostMapping("/test/write1")
    public String boardWriteForm(Board board, Model model){
        boardService.write(board); //글써짐
        model.addAttribute("message" , "글 작성이 완료되었습니다");
        model.addAttribute("searchUrl", "/test/list");
        return "message";
    }

    @GetMapping("/test/list")
    public String boardList(Model model, @PageableDefault //초기값 설정하는 부분 <Set 로 외워서 써야함>
            (page=0, size=10, sort="id", direction= Sort.Direction.DESC )
                Pageable pageable, @RequestParam(name="searchKeyword", defaultValue = "")
                    String searchKeyword){
        Page<Board> boardList;

        if(searchKeyword == null){
            boardList = boardService.boardList(pageable);
        } else {
            boardList = boardService.SearchList(searchKeyword, pageable);
        }

        int nowPage = boardList.getPageable().getPageNumber()+1; //처음 페이지 값이 초기치가 0이라서 +1을 해줘야함
        //.getPageNumber 를 붙여서 숫자로 변환이 됨. (없으면 "1" 이렇게 문자열이 넘어옴)

        int startPage = Math.max(nowPage-4,1); //Math 클래스의 max : 숫자를 비교함. 1보다 큰지 안큰지 1이 더 크면 1페이지가 나옴.
        int endPage = Math.min(nowPage+5,boardList.getTotalPages());
        //service 에서 boardList 에 findAll 을 해줬기 때문에 모든페이지가 다 들어가있음.

        model.addAttribute("board", boardList);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
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
        model.addAttribute("message" , "글 삭제가 완료되었습니다");
        model.addAttribute("searchUrl", "/test/list");
        return "message";
    }




}
