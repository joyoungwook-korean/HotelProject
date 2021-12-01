package com.springboot.st.signupProject.service;

import com.springboot.st.hotelProject.domain.Hotel_Room_Img;
import com.springboot.st.hotelProject.domain.Hotel_Room_ImgRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class Room_Img_S3DBService {
    private Hotel_Room_ImgRepository hotel_room_imgRepository;

    private S3Service service;



    public List<Hotel_Room_Img> saveImg_S3(List<MultipartFile> multipartFiles) throws IOException {
        List<Hotel_Room_Img> hotel_room_imgs = new ArrayList<>();
        System.out.println(multipartFiles.size());
        if(!multipartFiles.isEmpty()){
            for(MultipartFile multipartFile : multipartFiles) {
                String uuid = UUID.randomUUID().toString();
                Hotel_Room_Img save_Img = new Hotel_Room_Img();
                String origin_name = multipartFile.getOriginalFilename();
                String server_name = uuid + origin_name;
                String path = service.upload(multipartFile, server_name);

                save_Img.setImg_UUID(uuid);
                save_Img.setImg_Server_Path(path);
                save_Img.setImg_Server_Name(server_name);
                save_Img.setImg_Name(origin_name);
                Hotel_Room_Img hotel_room_img = hotel_room_imgRepository.save(save_Img);

                hotel_room_imgs.add(hotel_room_img);

            }
            return hotel_room_imgs;
        }
        return hotel_room_imgs;
    }

    public void delete_S3Img(List<Hotel_Room_Img> hotel_room_imgs){
        if(!hotel_room_imgs.isEmpty()){
            for(Hotel_Room_Img hotel_room_img : hotel_room_imgs){
                service.delete_Img(hotel_room_img);
                hotel_room_imgRepository.delete(hotel_room_img);
            }
        }
    }

}
