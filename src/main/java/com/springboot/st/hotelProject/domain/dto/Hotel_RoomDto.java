package com.springboot.st.hotelProject.domain.dto;


import com.springboot.st.hotelProject.domain.Hotel_Room;
import com.springboot.st.hotelProject.domain.Hotel_Room_Img;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class Hotel_RoomDto {

    private String id;

    @NotBlank(message = "roomNameは必修入力です")
    private String roomname;

    @Positive(message = "1以上")
    @NotNull(message = "Minは必修入力です")
    private Integer minpeople;

    @Positive(message = "1以上")
    @NotNull(message = "Maxは必修入力です")
    private Integer maxpeople;

    private String content;

    @NotNull
    private Integer price;

    private List<Hotel_Room_Img> hotel_room_imgs;

    private Integer roomcount;

    public static Hotel_Room create_room(Hotel_RoomDto hotel_roomDto){
        Hotel_Room hotel_room = Hotel_Room.builder().roomName(hotel_roomDto.getRoomname())
                .maxPeople(hotel_roomDto.getMaxpeople())
                .hotel_room_img(hotel_roomDto.getHotel_room_imgs())
                .content(hotel_roomDto.getContent())
                .minPeople(hotel_roomDto.getMinpeople())
                .roomcount(hotel_roomDto.getRoomcount())
                .price(hotel_roomDto.getPrice())
                .build();
        return hotel_room;
    }
    public static Hotel_Room create_room(Hotel_RoomDto hotel_roomDto, List<Hotel_Room_Img> hotel_room_imgs){
        Hotel_Room hotel_room = Hotel_Room.builder().roomName(hotel_roomDto.getRoomname())
                .maxPeople(hotel_roomDto.getMaxpeople())
                .hotel_room_img(hotel_roomDto.getHotel_room_imgs())
                .content(hotel_roomDto.getContent())
                .minPeople(hotel_roomDto.getMinpeople())
                .hotel_room_img(hotel_room_imgs)
                .price(hotel_roomDto.getPrice())
                .roomcount(hotel_roomDto.getRoomcount())
                .build();
        return hotel_room;
    }

}
