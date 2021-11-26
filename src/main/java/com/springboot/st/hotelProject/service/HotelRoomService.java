package com.springboot.st.hotelProject.service;

import com.springboot.st.hotelProject.domain.Hotel_Room;
import com.springboot.st.hotelProject.domain.Hotel_RoomRepository;
import com.springboot.st.hotelProject.domain.Hotel_Room_Img;
import com.springboot.st.hotelProject.domain.Hotel_Room_ImgRepository;
import com.springboot.st.hotelProject.domain.dto.Hotel_RoomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class HotelRoomService {

    private final Hotel_RoomRepository hotel_roomRepository;
    private final Hotel_Room_ImgRepository hotel_room_imgRepository;

    public List<Hotel_Room> all_find(){
        List<Hotel_Room> hotel_rooms = hotel_roomRepository.findAll();
        return hotel_rooms;
    }



    public Long save(Hotel_RoomDto hotel_roomDto, List<MultipartFile> multipartFile){
        Long save_id=null;
        if(!multipartFile.isEmpty()){
            try {
                List<Hotel_Room_Img> hotel_room_imgs =  img_Save(multipartFile);
                 save_id = hotel_roomRepository.save(Hotel_RoomDto.create_room(hotel_roomDto,hotel_room_imgs)).getId();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            save_id = hotel_roomRepository.save(Hotel_RoomDto.create_room(hotel_roomDto)).getId();
        }

        return save_id;
    }

    public Hotel_Room find_By_Idx(String idx){
        Long id = Long.parseLong(idx);
        return hotel_roomRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void delete_Room(Hotel_RoomDto hotel_roomDto){
        Hotel_Room hotel_room = find_By_Idx(hotel_roomDto.getId());
        List<Hotel_Room_Img> hotel_room_img = hotel_room.getHotel_room_img();
        hotel_roomRepository.delete(hotel_room);
        if(!hotel_room_img.isEmpty()){

            img_Delete(hotel_room_img);

        }
    }

    public void update_Room(Hotel_RoomDto hotel_roomDto, List<MultipartFile> multipartFiles) throws IOException {
        Hotel_Room hotel_room = find_By_Idx(hotel_roomDto.getId());
        hotel_room.setMax_People(hotel_roomDto.getMaxpeople());
        hotel_room.setMin_People(hotel_roomDto.getMinpeople());
        hotel_room.setPrice(hotel_roomDto.getPrice());
        hotel_room.setContent(hotel_roomDto.getContent());
        hotel_room.setRoomName(hotel_roomDto.getRoomname());
        hotel_room.setRoomCount(hotel_roomDto.getRoomCount());

        if(!multipartFiles.isEmpty()){
            List<Hotel_Room_Img> hotel_room_imgList = img_Save(multipartFiles);
            if(!hotel_room.getHotel_room_img().isEmpty()){
                List<Hotel_Room_Img> hotel_room_imgs_delete = hotel_room.getHotel_room_img();
                hotel_room.setHotel_room_img(null);
                img_Delete(hotel_room_imgs_delete);
                hotel_room.setHotel_room_img(hotel_room_imgList);
            }else{
                hotel_room.setHotel_room_img(hotel_room_imgList);
            }
        }
        hotel_roomRepository.save(hotel_room);
    }

    private void img_Delete(List<Hotel_Room_Img> hotel_room_imgList){
        for(Hotel_Room_Img hotel_room_img : hotel_room_imgList){
            hotel_room_imgRepository.delete(hotel_room_img);
            Path filePath = Paths.get(hotel_room_img.getImg_Server_Path());
            try{
                Files.delete(filePath);
            }catch (NoSuchFileException e){
                e.getStackTrace();
            }catch (IOException e){
                e.getStackTrace();
            }


        }
    }

    private List<Hotel_Room_Img> img_Save(List<MultipartFile> multipartFile) throws IOException {
        List<Hotel_Room_Img> hotel_room_imgs = new ArrayList<>();

        for(MultipartFile multipartFile1 : multipartFile){

            String uuid = UUID.randomUUID().toString();
            Hotel_Room_Img save_Img = new Hotel_Room_Img();
            String origin_name = multipartFile1.getOriginalFilename();
            String server_name = uuid+origin_name;
            String path = "src\\main\\resources\\static\\hotel\\assets\\img\\rooms";
            File file = new File(path);
            String absolute_Path = new File("").getAbsolutePath()+"\\";
            if(!file.exists()){
                file.mkdirs();
            }
            file = new File(absolute_Path+path+"/"+server_name);
            multipartFile1.transferTo(file);

            save_Img.setImg_UUID(uuid);
            save_Img.setImg_Server_Name(server_name);
            save_Img.setImg_Server_Path(file.getPath());
            save_Img.setImg_Name(origin_name);
            Hotel_Room_Img hotel_room_img1 =hotel_room_imgRepository.save(save_Img);

            hotel_room_imgs.add(hotel_room_img1);
        }

        return hotel_room_imgs;

    }


}
