package com.example.boardpractice.controller;


import com.example.boardpractice.dto.MemberResponseDto;
import com.example.boardpractice.dto.SignUpRequestDto;
import com.example.boardpractice.dto.SignUpResponseDto;
import com.example.boardpractice.dto.UpdatePasswordRequestDto;
import com.example.boardpractice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto){

        SignUpResponseDto signUpResponseDto =
                memberService.signUp(
                        requestDto.getUsername(),
                        requestDto.getPassword(),
                        requestDto.getAge());

        return new ResponseEntity<>(signUpResponseDto,HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> findById(@PathVariable Long id){

        MemberResponseDto memberResponseDto = memberService.findById(id);

        return new ResponseEntity<>(memberResponseDto,HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword (
    @PathVariable Long id,
    @RequestBody UpdatePasswordRequestDto requestdto) {
        memberService.updatePassword(id,requestdto.getOldPassword(),requestdto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }


}
