package com.example.boardpractice.repository;


import com.example.boardpractice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByUsername(String username);

    default Member findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()->
                new ResponseStatusException(HttpStatus.NO_CONTENT,
                        "존재하지 않는 아이디입니다."));
    }

    default Member findMemberByUsernameOrElseThrow(String username){
        return findMemberByUsername(username).orElseThrow(
                ()-> new ResponseStatusException(
                        HttpStatus.NO_CONTENT,
                "존재하지 않는 아이디입니다."));
    }


}
