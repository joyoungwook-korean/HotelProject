package com.springboot.st.hotelProject.domain;

import com.springboot.st.domain.BaseTimeEntity;
import com.springboot.st.domain.pay.Payment;
import com.springboot.st.domain.user.User;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Hotel_Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Payment payment;

    @OneToOne
    private Hotel_Room reHotelRoom;

    @OneToOne
    private User user;

    private String anotherUser;

    private int people;

    private String startDay;

    private String finishDay;

    private String phoneNum;

    @OneToMany
    private List<Hotel_Reservation_AllDay> hotel_reservation_allDays;

    @Builder
    public Hotel_Reservation(Payment payment, String phoneNum,Hotel_Room re_hotel_room, User user, String another_user, int people, String startDay, String finishDay, List<Hotel_Reservation_AllDay> hotel_reservation_allDays) {
        this.payment = payment;
        this.reHotelRoom = re_hotel_room;
        this.user = user;
        this.anotherUser = another_user;
        this.people = people;
        this.phoneNum = phoneNum;
        this.startDay = startDay;
        this.finishDay = finishDay;
        this.hotel_reservation_allDays = hotel_reservation_allDays;
    }
}
