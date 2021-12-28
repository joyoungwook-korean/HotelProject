package com.springboot.st.hotelProject.service;

import com.springboot.st.hotelProject.domain.Hotel_Board;
import com.springboot.st.hotelProject.domain.Hotel_BoardRepository;
import com.springboot.st.hotelProject.domain.dto.HotelBoardDto;
import com.springboot.st.signupProject.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelBoardService {

    private final S3Service s3service;
    private final Hotel_BoardRepository hotel_boardRepository;


    public Page<Hotel_Board> find_all_board(Pageable pageable){
        return hotel_boardRepository.findAllByOrderByIdDesc(pageable);
    }

    @Transactional(readOnly = true)
    public HotelBoardDto hotelBoardDto(Long id) {
        Hotel_Board hotel_board = hotel_boardRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        HotelBoardDto hotelBoardDto = HotelBoardDto.of(hotel_board);
        return hotelBoardDto;
    }

    public Hotel_Board save(MultipartFile multipartFile,
                            String title, String content) {

        Map<String, String> imgPath = s3service.serverSaveMap(multipartFile);

        Hotel_Board hotel_board =
                new Hotel_Board().builder().
                        content(content).
                        imgPath(imgPath.get("serverURL")).
                        imgOriginName(imgPath.get("originName")).
                        imgUuid(imgPath.get("uuid")).
                        title(title).
                                build();

        hotel_boardRepository.save(hotel_board);

        return hotel_board;

    }

    public void delete(Hotel_Board hotel_board){
        s3service.delete_Img(hotel_board.getImgPath());
        hotel_boardRepository.delete(hotel_board);
    }



}
