package com.springboot.st.hotelProject.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Hotel_Reservation_AllDayRepository extends JpaRepository<Hotel_Reservation_AllDay,Long> {

    @Override
    List<Hotel_Reservation_AllDay> findAll();
}
