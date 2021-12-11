package com.springboot.st.hotelProject.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Hotel_ReservationRepository extends JpaRepository<Hotel_Reservation,Long> {
    List<Hotel_Reservation> findByStartDayContains(String searchReservation);
    List<Hotel_Reservation> findByPhoneNumContains(String searchReservation);
}
