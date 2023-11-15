package com.likelion.picpic.controller;

import com.likelion.picpic.service.S3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/frame")
@RequiredArgsConstructor
@RestController
@Api(tags = "Frame",description = "Frame 관련 로직 작성")
public class FrameController {
    private final S3Service s3Service;

    @PostMapping("/save/frame")
    @ApiOperation(value = "프레임 저장 api", notes = "헤더로 토큰, 바디로 이미지 주면돼")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패")
    })
    public ResponseEntity<?> saveFrame(@RequestPart MultipartFile frame,
                                       Authentication authentication)
            throws IOException {
        String email=authentication.getName();
        s3Service.saveFile("frame",email, frame);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Frame 가져오기", notes = "유저의 모든 Frame을 url리스트로 가져오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패")
    })
    @GetMapping("/get/frame")
    public ResponseEntity<List<String>> findFrame(Authentication authentication){
        List<String> urlsList=s3Service.findImageUrlsByUserId(authentication.getName(), "frame");
        return ResponseEntity.ok().body(urlsList);
    }

    @ApiOperation(value = "특정 Frame 지우기", notes = "이미지명으로 받아야돼")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패")
    })
    @DeleteMapping("/delete/frame/{productId}")
    public ResponseEntity<?> deleteFrame(Authentication authentication,
                                         String url){
        s3Service.deleteImage("url");
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "스티커 가져오기", notes = "테마 이름 줘야돼")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패")
    })
    @GetMapping("/get/sticker/{theme}")
    public ResponseEntity<List<String>> getStickerList(Authentication authentication,
                                         @PathVariable String theme){
        List<String> stickerList=s3Service.findStickerImageUrls("sticker", theme);
        return ResponseEntity.ok().body(stickerList);
    }

}
