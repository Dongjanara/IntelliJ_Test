package com.Test.test.service;

import com.Test.test.entity.Board;
import com.Test.test.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board) {
        boardRepository.save(board);
    }

    //게시글 list 처리-전체 불러오기
    public List<Board> boardList(){
        return boardRepository.findAll();
    }

    //게시글 눌렀을때, 내용까지 나오게
    public Board boardview(Integer id) {
        return boardRepository.findById(id).get();
    }


}
