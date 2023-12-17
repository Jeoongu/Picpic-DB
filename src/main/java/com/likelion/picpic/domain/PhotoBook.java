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

    @ElementCollection  //컬렉션을 칼럼으로 매핑해주는 어노테이션 (별도의 테이블 생성)
    @CollectionTable(name = "photo_book_photos", joinColumns = @JoinColumn(name = "photo_book_id")) //임시로 생성할 테이블 설정
    @Column(name = "photos")
    private List<String> photos;  //동적 포토북 사진 리스트

//    public static PhotoBook from(User userF, CreatePhotoBookDto createPhotoBookDto){
//        return PhotoBook.builder()
//                .user(userF)
//                .name(createPhotoBookDto.getName())
//                .photos(createPhotoBookDto.getAddPhotoList())
//                .build();
//    }

    public static PhotoBook from(User userF, CreatePhotoBookDto createPhotoBookDto){
        PhotoBook photoBook = PhotoBook.builder()
                .user(userF)
                .name(createPhotoBookDto.getName())
                .photos(createPhotoBookDto.getAddPhotoList())
                .build();
        userF.setPhotoBook(photoBook);
        return photoBook;
    }
}
