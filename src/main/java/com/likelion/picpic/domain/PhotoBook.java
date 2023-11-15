package com.likelion.picpic.domain;

import com.likelion.picpic.dto.CreatePhotoBookDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "photoBook")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhotoBook {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_book_id")
    private Long id;

    @OneToOne(mappedBy = "photoBook")
    private User user;

    @OneToMany(mappedBy = "photoBook")
    private final List<Memo> memoList=new ArrayList<>();

    @Column
    private String name;

    @Column
    private List<String> photoList;  //동적 포토북 사진 리스트

    public static PhotoBook from(User userF, CreatePhotoBookDto createPhotoBookDto){
        return PhotoBook.builder()
                .user(userF)
                .name(createPhotoBookDto.getName())
                .photoList(createPhotoBookDto.getAddPhotoList())
                .build();
    }
}
