package com.likelion.picpic.controller;

import com.likelion.picpic.dto.UserEmailAndPassword;
import com.likelion.picpic.dto.UserJoinDto;
import com.likelion.picpic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserJoinDto userJoinDto){
        userService.join(userJoinDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEmailAndPassword userEmailAndPassword){
        //토큰만 발급됨(로그인 성공시)
        String email= userEmailAndPassword.getEmail();
        String password= userEmailAndPassword.getPassword();
        return ResponseEntity.ok().body(userService.login(email, password));
    }
}
