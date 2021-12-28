package com.springboot.st.hotelProject.domain.dto;

import com.springboot.st.domain.pay.Payment;
import com.springboot.st.domain.user.User;
import com.springboot.st.hotelProject.domain.Hotel_Reservation;
import com.springboot.st.hotelProject.domain.Hotel_Room;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;


@Getter
@Setter
public class HotelReservationDto {

    private Long id;

    private Payment payment;

    private Hotel_Room reHotelRoom;

    private User user;

    private String anotherUser;

    private int people;

    private String startDay;

    private String finishDay;

    private String phoneNum;

    private String createdDate;

    private Long paymentId;

    private static ModelMapper modelMapper = new ModelMapper();

    public static HotelReservationDto of(Hotel_Reservation hotel_reservation) {
        return modelMapper.map(hotel_reservation, HotelReservationDto.class);
    }
}
