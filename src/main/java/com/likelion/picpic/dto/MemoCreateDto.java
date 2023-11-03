package com.likelion.picpic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemoCreateDto {
    private int x;
    private int y;
    private int pNum;
    private String content;
}
