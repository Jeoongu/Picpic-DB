package com.likelion.picpic.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Frame {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "frame_id")
    private Long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @Column
    private String frame;  //프레임 이미지의 url
}
