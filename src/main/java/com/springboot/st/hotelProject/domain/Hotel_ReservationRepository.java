package com.springboot.st.hotelProject.domain;

import com.springboot.st.domain.pay.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Hotel_ReservationRepository extends JpaRepository<Hotel_Reservation,Long> {
    Page<Hotel_Reservation> findByPhoneNumContains(String search, Pageable pageable);
    Page<Hotel_Reservation> findByStartDayContains(String search, Pageable pageable);

    Hotel_Reservation findByIdAndPhoneNum(Long id, String phone);

    Hotel_Reservation findByPaymentAndPhoneNum(Payment payment,String phone);
}
