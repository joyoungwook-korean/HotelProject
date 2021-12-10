package com.springboot.st.hotelProject.service;

import com.springboot.st.domain.user.User;
import com.springboot.st.hotelProject.domain.Hotel_Reservation;
import com.springboot.st.hotelProject.domain.Hotel_ReservationRepository;
import com.springboot.st.hotelProject.domain.Hotel_Reservation_AllDay;
import com.springboot.st.hotelProject.domain.Hotel_Room;
import com.springboot.st.signupProject.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;

@Service
@NoArgsConstructor
@Transactional
public class HotelReservationService {

    @Autowired
    Hotel_ReservationRepository hotel_reservationRepository;
    @Autowired
    UserService userService;

    @Autowired
    HotelReservationAllDayService hotelReservationAllDayService;

    @Autowired
    HotelRoomService hotelRoomService;


    public List<Hotel_Room> find_hotel_Room_rest(String startDay, String finishDay, int people, String room){
        return null;
    }

    public Hotel_Reservation save(Map<String, Object> reservation_map){
        Hotel_Reservation hotel_reservation = null;
        User user =null;
        String another_user=null;
        Hotel_Room hotel_room = hotelRoomService.find_room_name((String)reservation_map.get("room"));

        List<Hotel_Reservation_AllDay> hotel_reservation_allDays =
               hotelReservationAllDayService.save((String)reservation_map.get("checkin"),
                       (String) reservation_map.get("checkout"),
                       (String)reservation_map.get("room"));

        if(reservation_map.get("user")!=null){
            Map<String,Object> user_map = (Map<String, Object>) reservation_map.get("user");
            user = userService.get_user_for_userid((String)user_map.get("userid"));

        }else{
            another_user = (String)reservation_map.get("another_user");
        }
        hotel_reservation = new Hotel_Reservation().builder().another_user(another_user)
                .people(Integer.parseInt((String)reservation_map.get("people")) )
                .start_day((String) reservation_map.get("checkin"))
                .finish_day((String) reservation_map.get("checkout"))
                .user(user)
                .re_hotel_room(hotel_room)
                .phone_num((String)reservation_map.get("phone"))
                .payment(null)
                .hotel_reservation_allDays(hotel_reservation_allDays)
                .build();
        hotel_reservationRepository.save(hotel_reservation);
        return hotel_reservation;
    }





}
