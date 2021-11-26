package com.springboot.st.hotelProject.domain;

import com.springboot.st.domain.BaseTimeEntity;
import com.springboot.st.domain.user.Role;
import lombok.Builder;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Data
public class Hotel_Room extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String roomName;

    private int min_People;

    private int max_People;

    private String content;

    private int price;

    private int roomcount;

    @OneToMany
    private List<Hotel_Room_Img> hotel_room_img;

    @Builder
    public Hotel_Room(String roomName,int price ,int min_People,int roomCount, int max_People, String content, List<Hotel_Room_Img> hotel_room_img) {
        this.roomName = roomName;
        this.min_People = min_People;
        this.max_People = max_People;
        this.content = content;
        this.price = price;
        this.roomcount = roomCount;
        this.hotel_room_img = hotel_room_img;
    }

    public Hotel_Room() {

    }
}
