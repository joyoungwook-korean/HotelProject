package com.springboot.st.hotelProject.domain;

import com.springboot.st.domain.user.Role;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Hotel_Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    private int min_People;

    private int max_People;

    private String content;

    @OneToMany
    private List<Hotel_Room_Img> hotel_room_img;

    @Builder
    public Hotel_Room(String roomName, int min_People, int max_People, String content, List<Hotel_Room_Img> hotel_room_img) {
        this.roomName = roomName;
        this.min_People = min_People;
        this.max_People = max_People;
        this.content = content;
        this.hotel_room_img = hotel_room_img;
    }

    public Hotel_Room() {

    }
}
