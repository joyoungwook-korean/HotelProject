package com.springboot.st.hotelProject.domain;

import com.springboot.st.domain.BaseTimeEntity;
import com.springboot.st.domain.pay.Payment;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
public class Hotel_Reservation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Payment payment;

    @OneToOne
    private Hotel_Room re_hotel_room;

    private Timestamp start_day;

    private Timestamp finish_day;

    @Builder
    public Hotel_Reservation(Payment payment, Hotel_Room re_hotel_room, Timestamp start_day, Timestamp finish_day) {
        this.payment = payment;
        this.re_hotel_room = re_hotel_room;
        this.start_day = start_day;
        this.finish_day = finish_day;
    }
}
