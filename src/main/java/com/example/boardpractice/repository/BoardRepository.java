package com.example.boardpractice.repository;

import com.example.boardpractice.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface BoardRepository extends JpaRepository<Board,Long> {

    default Board findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NO_CONTENT,
                        "존재하지 않는 아이디입니다."));
    }
}
