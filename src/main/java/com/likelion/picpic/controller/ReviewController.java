package com.likelion.picpic.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
public class ReviewController {
    @PostMapping
    public ResponseEntity<String> writeReview(Authentication authentication){
        //토큰을 함께 받아야만 작성할 수 있도록 함
        return ResponseEntity.ok().body(
                authentication.getName()+"님의 리뷰 등록이 완료되었습니다.");
    }
}
