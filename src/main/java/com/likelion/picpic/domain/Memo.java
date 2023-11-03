package com.likelion.picpic.domain;

import com.likelion.picpic.dto.MemoCreateDto;
import lombok.*;

import javax.persistence.*;

@Entity(name = "memo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Memo {
    @ManyToOne(targetEntity = PhotoBook.class, fetch = FetchType.LAZY)
    @JoinColumn(name="photo_book_id")
    private PhotoBook photoBook;

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private int x;

    @Column
    private int y;

    @Column
    private int pNum;

    @Column
    private String content;

    public static Memo from(MemoCreateDto memoCreateDto, PhotoBook photoBook){
        return Memo.builder()
                .x(memoCreateDto.getX())
                .y(memoCreateDto.getY())
                .pNum(memoCreateDto.getPNum())
                .content(memoCreateDto.getContent())
                .photoBook(photoBook)
                .build();
    }
}
