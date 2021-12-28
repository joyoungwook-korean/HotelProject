package com.springboot.st.hotelProject.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface Hotel_BoardRepository extends JpaRepository<Hotel_Board,Long> {
    Page<Hotel_Board> findAllByOrderByIdDesc(Pageable pageable);
}
