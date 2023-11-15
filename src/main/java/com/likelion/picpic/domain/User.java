package com.likelion.picpic.domain;

import com.likelion.picpic.dto.UserJoinDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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

    @OneToMany(mappedBy = "user")
    private final List<Frame> frames=new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private final List<Photo> photoList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="photo_book_id")
    private PhotoBook photoBook;

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
