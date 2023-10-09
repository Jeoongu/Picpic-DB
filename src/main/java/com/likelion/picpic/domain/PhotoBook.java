package com.likelion.picpic.domain;

import lombok.*;

import javax.persistence.*;

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

    @Column
    private String name;

    public static PhotoBook from(User userF, String nameF){
        return PhotoBook.builder()
                .user(userF)
                .name(nameF)
                .build();
    }
}
