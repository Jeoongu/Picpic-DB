package com.likelion.picpic.controller;

import com.likelion.picpic.domain.User;
import com.likelion.picpic.service.PhotoService;
import com.likelion.picpic.service.S3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequestMapping("/photo")
@RequiredArgsConstructor
@RestController
@Api(tags = "Photo",description = "프레임 적용한 사진 관련 로직 작성")
public class PhotoController{

    private final S3Service s3Service;
    private final PhotoService photoService;

    @PostMapping("/save")
    @ApiOperation(value = "사진 저장 api", notes = "헤더로 토큰, 바디로 이미지 주면돼")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패")
    })
    public ResponseEntity<?> savePhoto(@RequestPart MultipartFile photo,
                                       Authentication authentication)
            throws IOException{
        String email=authentication.getName();
        String url=s3Service.saveFile("photo",email,photo);
        Long userId=s3Service.getUserId(email);
        photoService.savePhoto(userId,url);
        return ResponseEntity.ok().build();
    }
}
