package com.springboot.st.hotelProject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Hotel_RoomRepository extends JpaRepository<Hotel_Room,Long> {

    Hotel_Room findHotel_RoomByRoomName(String roomname);

    List<Hotel_Room> findByMaxPeopleGreaterThanEqual(int people);


}
