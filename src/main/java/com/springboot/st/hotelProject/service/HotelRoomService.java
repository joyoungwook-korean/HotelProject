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

    public Hotel_Room find_By_Idx(String idx){
        Long id = Long.parseLong(idx);
        return hotel_roomRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void delete_Room(Hotel_RoomDto hotel_roomDto){
        Hotel_Room hotel_room = find_By_Idx(hotel_roomDto.getId());
        hotel_roomRepository.delete(hotel_room);
    }

    public void update_Room(Hotel_RoomDto hotel_roomDto){
        Hotel_Room hotel_room = find_By_Idx(hotel_roomDto.getId());
        hotel_room.setMax_People(hotel_roomDto.getMaxpeople());
        hotel_room.setMin_People(hotel_roomDto.getMinpeople());
        hotel_room.setContent(hotel_roomDto.getContent());
        hotel_room.setRoomName(hotel_roomDto.getRoomname());
        hotel_roomRepository.save(hotel_room);
    }


}
