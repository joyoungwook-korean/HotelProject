package com.springboot.st.hotelProject.domain;

import com.springboot.st.domain.BaseTimeEntity;
import com.springboot.st.domain.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Hotel extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String hotel_Name;

    @ManyToOne
    private Hotel_Room hotel_room;

    @OneToOne
    private User manager;

}
