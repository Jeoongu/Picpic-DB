package com.likelion.picpic.dto;

import com.likelion.picpic.domain.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetPhotosAndMemosDto {
    private List<String> photoList;
    private List<Memo> memoList;
}
