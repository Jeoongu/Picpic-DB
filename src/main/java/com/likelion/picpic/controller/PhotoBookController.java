package com.likelion.picpic.controller;

import com.likelion.picpic.domain.Memo;
import com.likelion.picpic.dto.CreatePhotoBookDto;
import com.likelion.picpic.repository.PhotoBookRepository;
import com.likelion.picpic.service.PhotoBookService;
import com.likelion.picpic.service.S3Service;
import com.likelion.picpic.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/photoBook")
@RequiredArgsConstructor
@RestController
@Api(tags = "PhotoBook",description = "포토북 관련 로직 작성")
public class PhotoBookController {
    private final PhotoBookService photoBookService;
    private final S3Service s3Service;

    @ApiOperation(value = "포토북 저장 api", notes = "헤더로 토큰, 바디로 JSON(name, addPhotoList")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패")
    })
    @PostMapping("/save")
    public ResponseEntity<?> savePhotoBook(Authentication authentication,
                                           @RequestBody CreatePhotoBookDto createPhotoBookDto){
        Long userId= s3Service.getUserId(authentication.getName());
        photoBookService.savePhotoBook(userId, createPhotoBookDto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "포토북 사진 리스트, 메모 리스트 가져오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패")
    })
    @GetMapping("/get/{uuid}")
    public ResponseEntity<?> getPhotoList(@PathVariable("uuid") String uuid){
        return ResponseEntity.ok().body(photoBookService.getPhotoList(uuid));
    }
}
