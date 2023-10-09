package com.likelion.picpic.controller;

import com.likelion.picpic.service.S3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequestMapping("/frame")
@RequiredArgsConstructor
@RestController
@Api(tags = "Frame",description = "Frame 관련 로직 작성")
public class FrameController {
    private final S3Service s3Service;

    @PostMapping("/save")
    @ApiOperation(value = "프레임 저장 api", notes = "헤더로 토큰, 바디로 이미지 주면돼")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패")
    })
    public ResponseEntity<?> saveFrame(@RequestBody MultipartFile frame,
                                       @RequestHeader("Authorization")String token)
            throws IOException {
        s3Service.saveFile("frame",token, frame);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Frame 가져오기", notes = "유저의 모든 Frame을 url리스트로 가져오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패")
    })
    @GetMapping("/find")
    public ResponseEntity<List<String>> findFrame(@RequestHeader("Authorization")String token){
        List<String> urlsList=s3Service.findImageUrlsByUserId(token, "frame");
        return ResponseEntity.ok().body(urlsList);
    }

    @ApiOperation(value = "특정 Frame 지우기", notes = "이미지명으로 받아야돼")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패")
    })
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteFrame(@PathVariable("productId") Long productId,
                                         @RequestHeader("Authorization")String token){
        //TODO: 이거 프레임 아이디 어떻게 프론트가 알고 보내지?
        //TODO: S3Service에서 이미지명으로 지우던데 이미지명은 어떻게 알지?
        s3Service.deleteImage("NULL");
        return ResponseEntity.ok().build();
    }
}
