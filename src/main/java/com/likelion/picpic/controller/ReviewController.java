package com.likelion.picpic.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
@Api(tags = "Review",description = "포토북 관련 로직 작성")
public class ReviewController {
    @PostMapping
    @ApiOperation(value = "실험용", notes = "실험중")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패")
    })
    public ResponseEntity<String> writeReview(Authentication authentication){
        //토큰을 함께 받아야만 작성할 수 있도록 함
        return ResponseEntity.ok().body(
                authentication.getName()+"의 리뷰.");
    }
}
