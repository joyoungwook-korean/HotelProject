package com.springboot.st.hotelProject.service;

import com.springboot.st.hotelProject.domain.Hotel_Board;
import com.springboot.st.hotelProject.domain.Hotel_BoardRepository;
import com.springboot.st.signupProject.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelBoardService {

    private final S3Service s3service;
    private final Hotel_BoardRepository hotel_boardRepository;

    public Hotel_Board save(MultipartFile multipartFile,
                            HashMap<String, Object> params) {

        Map<String, String> imgPath = s3service.serverSaveMap(multipartFile);

        Hotel_Board hotel_board =
                new Hotel_Board().builder().
                        content((String)params.get("content")).
                        imgPath(imgPath.get("serverURL")).
                        imgOriginName(imgPath.get("originName")).
                        imgUuid(imgPath.get("uuid")).
                        title((String)params.get("title")).
                                build();

        hotel_boardRepository.save(hotel_board);

        return hotel_board;

    }

    public void delete(Hotel_Board hotel_board){
        s3service.delete_Img(hotel_board.getImgPath());
        hotel_boardRepository.delete(hotel_board);
    }



}
