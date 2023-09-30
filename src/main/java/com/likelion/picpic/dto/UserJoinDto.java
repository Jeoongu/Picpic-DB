package com.likelion.picpic.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserJoinDto {
    private String email;
    private String password;
    private String nickname;
    private String image;
    private int birth;
    private String gender;
}
