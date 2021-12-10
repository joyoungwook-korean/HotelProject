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
    private Hotel_Room re_hotel_room;

    @OneToOne
    private User user;

    private String another_user;

    private int people;

    private String start_day;

    private String finish_day;

    private String phone_num;

    @OneToMany
    private List<Hotel_Reservation_AllDay> hotel_reservation_allDays;

    @Builder
    public Hotel_Reservation(Payment payment, String phone_num,Hotel_Room re_hotel_room, User user, String another_user, int people, String start_day, String finish_day, List<Hotel_Reservation_AllDay> hotel_reservation_allDays) {
        this.payment = payment;
        this.re_hotel_room = re_hotel_room;
        this.user = user;
        this.another_user = another_user;
        this.people = people;
        this.phone_num = phone_num;
        this.start_day = start_day;
        this.finish_day = finish_day;
        this.hotel_reservation_allDays = hotel_reservation_allDays;
    }
}
