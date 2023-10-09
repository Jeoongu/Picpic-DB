package com.likelion.picpic.controller;

import com.likelion.picpic.dto.UserEmailAndPassword;
import com.likelion.picpic.dto.UserJoinDto;
import com.likelion.picpic.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "로그인 api", notes = "jwt 로그인 api")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 401, message = "로그인 실패")
    })
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserEmailAndPassword userEmailAndPassword){
        //토큰만 발급됨(로그인 성공시)
        String email= userEmailAndPassword.getEmail();
        String password= userEmailAndPassword.getPassword();
        return ResponseEntity.ok().body(userService.login(email, password));
    }
}
