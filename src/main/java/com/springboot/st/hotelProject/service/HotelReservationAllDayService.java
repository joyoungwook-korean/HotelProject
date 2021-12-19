package com.springboot.st.hotelProject.service;

import com.springboot.st.hotelProject.domain.Hotel_Reservation_AllDay;
import com.springboot.st.hotelProject.domain.Hotel_Reservation_AllDayRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@NoArgsConstructor
@Transactional
public class HotelReservationAllDayService {

    @Autowired
    Hotel_Reservation_AllDayRepository hotel_reservation_allDayRepository;


    public List<Hotel_Reservation_AllDay> save(String startDay, String endDay,String roomName){
        List<String> string_All_Date = allDay_check(startDay,endDay);
        List<Hotel_Reservation_AllDay> hotel_reservation_allDays = new ArrayList<>();

        for(String date : string_All_Date){
            Hotel_Reservation_AllDay hotelReservationAllDay =
                    new Hotel_Reservation_AllDay();
            hotelReservationAllDay.setDay(date);
            hotelReservationAllDay.setRoomName(roomName);
            Hotel_Reservation_AllDay hotelReservationAllDay1 =
                    hotel_reservation_allDayRepository.save(hotelReservationAllDay);
            hotel_reservation_allDays.add(hotelReservationAllDay1);
        }

        return hotel_reservation_allDays;
    }

    public List<Hotel_Reservation_AllDay> find_all(){
        return hotel_reservation_allDayRepository.findAll();
    }

    public List<String> allDay_check(String checkin, String checkout){
        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date checkin_for_in = null;
        Date checkout_for_in = null;
        ArrayList<String> arrayList = new ArrayList<>();

        Date current_Date = null;
        try {
            checkin_for_in = df.parse(checkin);
            checkout_for_in = df.parse(checkout);
            current_Date = checkin_for_in;
            while (current_Date.compareTo(checkout_for_in) <= 0){
                arrayList.add(df.format(current_Date));
                Calendar c= Calendar.getInstance();
                c.setTime(current_Date);
                c.add(Calendar.DAY_OF_MONTH,1);
                current_Date = c.getTime();
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return arrayList;
    }
}
