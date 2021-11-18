package com.springboot.st.hotelProject.service;

import com.springboot.st.hotelProject.domain.Hotel_Room;
import com.springboot.st.hotelProject.domain.Hotel_RoomRepository;
import com.springboot.st.hotelProject.domain.dto.Hotel_RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelRoomService {

    private final Hotel_RoomRepository hotel_roomRepository;

    public List<Hotel_Room> all_find(){
        List<Hotel_Room> hotel_rooms = hotel_roomRepository.findAll();
        return hotel_rooms;
    }

    public Long save(Hotel_RoomDto hotel_roomDto){
        Long save_id =
                hotel_roomRepository.save(Hotel_RoomDto.create_room(hotel_roomDto)).getId();
        return save_id;
    }

}
