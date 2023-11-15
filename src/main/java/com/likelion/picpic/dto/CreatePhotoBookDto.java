package com.likelion.picpic.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreatePhotoBookDto {
    private String name;
    private List<String> addPhotoList;
}
