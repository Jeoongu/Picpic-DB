package com.likelion.picpic.domain;

import com.likelion.picpic.dto.UserJoinDto;
import lombok.*;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String nickname;

    @Column
    private String image;

    @Column
    private int birth;

    @Column
    private String gender;

    public static User from(UserJoinDto userDto){
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .nickname(userDto.getNickname())
                .image(userDto.getImage())
                .birth(userDto.getBirth())
                .gender(userDto.getGender())
                .build();
    }
}
