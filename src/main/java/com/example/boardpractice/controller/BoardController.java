package com.example.boardpractice.controller;


import com.example.boardpractice.dto.BoardResponseDto;
import com.example.boardpractice.dto.BoardWithAgeResponseDto;
import com.example.boardpractice.dto.CreateBoardRequestDto;
import com.example.boardpractice.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardResponseDto> save(
            @RequestBody CreateBoardRequestDto requestDto){

        BoardResponseDto save =
                boardService.save(
                        requestDto.getTitle(),
                        requestDto.getContents(),
                        requestDto.getUsername());

        return new ResponseEntity<>(save,HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll(){
        List<BoardResponseDto> dtoList = boardService.findAll();

        return new ResponseEntity<>(dtoList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardWithAgeResponseDto> findById(
            @PathVariable Long id){
        BoardWithAgeResponseDto boardWithage = boardService.findByID(id);

        return new ResponseEntity<>(boardWithage,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boardService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
