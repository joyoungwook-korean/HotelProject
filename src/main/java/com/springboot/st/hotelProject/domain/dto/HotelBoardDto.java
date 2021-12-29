package com.springboot.st.hotelProject.domain.dto;

import com.springboot.st.domain.user.User;
import com.springboot.st.hotelProject.domain.Hotel_Board;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class HotelBoardDto {

    private String id;

    private String title;

    private String content;

    private String imgPath;

    private User user;

    private static ModelMapper modelMapper = new ModelMapper();

    public static HotelBoardDto of(Hotel_Board hotel_board){
        return modelMapper.map(hotel_board, HotelBoardDto.class);
    }
}
