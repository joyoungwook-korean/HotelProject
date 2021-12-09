package com.springboot.st.hotelProject.service;

import com.springboot.st.domain.user.User;
import com.springboot.st.hotelProject.domain.Hotel_Reservation;
import com.springboot.st.hotelProject.domain.Hotel_ReservationRepository;
import com.springboot.st.hotelProject.domain.Hotel_Room;
import com.springboot.st.signupProject.service.UserService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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




    public List<Hotel_Room> find_hotel_Room_rest(String startDay, String finishDay, int people, String room){
        return null;
    }

    public void save(Map<String, Object> reservation_map){

        Hotel_Reservation hotel_reservation = null;
        User user =null;

        if(reservation_map.get("user")!=null){
            Map<String,Object> user_map = (Map<String, Object>) reservation_map.get("user");
            user = userService.get_user_for_userid((String)user_map.get("userid"));

        }
        hotel_reservation = new Hotel_Reservation().builder().build();

    }




}
