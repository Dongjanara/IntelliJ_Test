package com.Test.test.service;

import com.Test.test.entity.Board;
import com.Test.test.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public void write(Board board) {
        boardRepository.save(board);
    }

    //게시글 list 처리-전체 불러오기 ==> 페이징 처리를 위해서 List<Board> 형태로 받아오는걸 public Page<Board> 로 변경함
    public Page<Board> boardList(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    //게시글 눌렀을때, 내용까지 나오게
    public Board boardview(Integer id) {
        return boardRepository.findById(id).get();
    }

    //게시글 삭제하기
    public void delete(Integer id) {
        boardRepository.deleteById(id);
    }


}
