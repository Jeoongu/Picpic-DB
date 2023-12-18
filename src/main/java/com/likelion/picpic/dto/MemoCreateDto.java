package com.likelion.picpic.dto;

import com.likelion.picpic.domain.Memo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemoCreateDto {
    private int x;
    private int y;
    private int pageNum;
    private String content;
    private int emojiNum;

    public static MemoCreateDto from(Memo memo){
        return MemoCreateDto.builder()
                .x(memo.getX())
                .y(memo.getY())
                .pageNum(memo.getPageNum())
                .content(memo.getContent())
                .emojiNum(memo.getEmojiNum())
                .build();
    }
}
