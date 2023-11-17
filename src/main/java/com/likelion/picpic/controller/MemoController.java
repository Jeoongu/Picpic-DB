package com.likelion.picpic.controller;

import com.likelion.picpic.domain.Memo;
import com.likelion.picpic.dto.MemoCreateDto;
import com.likelion.picpic.service.MemoService;
import com.likelion.picpic.service.S3Service;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/memo")
@RequiredArgsConstructor
@RestController
@Api(tags = "memo",description = "메모 관련 로직 작성")
public class MemoController {
    private final MemoService memoService;
    private final S3Service s3Service;

    @ApiOperation(value = "메모 저장 api", notes = "바디로 메모dto주면돼")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패")
    })
    @PostMapping("/create/{uuid}")
    public ResponseEntity<?> createMemo(@PathVariable("uuid")String uuid,
                                        MemoCreateDto memoCreateDto){
        memoService.saveMemo(memoCreateDto, uuid);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "메모 삭제 api", notes = "바디로 메모dto주면돼")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "실패")
    })
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMemo(Authentication authentication,
                                        MemoCreateDto memoCreateDto){
        memoService.deleteMemo(memoCreateDto);
        return ResponseEntity.ok().build();
    }
}
