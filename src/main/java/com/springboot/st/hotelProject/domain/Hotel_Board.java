package com.springboot.st.hotelProject.domain;

import com.springboot.st.domain.BaseTimeEntity;
import com.springboot.st.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Hotel_Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private String imgPath;

    private String imgUuid;

    private String imgOriginName;

    @ManyToOne
    private User user;

    @Builder
    public Hotel_Board(String title, String content, String imgPath, String imgUuid, String imgOriginName,User user) {
        this.title = title;
        this.content = content;
        this.imgPath = imgPath;
        this.imgUuid = imgUuid;
        this.imgOriginName = imgOriginName;
        this.user =user;
    }
}
