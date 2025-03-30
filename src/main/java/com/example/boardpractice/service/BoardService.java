package com.example.boardpractice.service;


import com.example.boardpractice.dto.BoardResponseDto;
import com.example.boardpractice.dto.BoardWithAgeResponseDto;
import com.example.boardpractice.entity.Board;
import com.example.boardpractice.entity.Member;
import com.example.boardpractice.repository.BoardRepository;
import com.example.boardpractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;


    public BoardResponseDto save(String title,String contents, String username) {

        Member memberByUsernameOrElseThrow = memberRepository.findMemberByUsernameOrElseThrow(username);

        Board board = new Board(title,contents);
        board.setMember(memberByUsernameOrElseThrow);

        Board savedBoard = boardRepository.save(board);

        return new BoardResponseDto(savedBoard.getId(),savedBoard.getTitle(),savedBoard.getContents());
    }

    public List<BoardResponseDto> findAll() {

       return boardRepository.findAll().stream()
                .map(BoardResponseDto :: toDto)
                .toList();

    }

    public BoardWithAgeResponseDto findByID(Long id) {

        Board findboard = boardRepository.findByIdOrElseThrow(id);
        Member writer = findboard.getMember();
        return new BoardWithAgeResponseDto(findboard.getTitle(),findboard.getContents(),writer.getAge());
    }

    public void delete(Long id) {

        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        boardRepository.delete(findBoard);

    }
}
