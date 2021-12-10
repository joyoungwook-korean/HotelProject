package com.springboot.st.hotelProject.service;

import com.springboot.st.hotelProject.domain.*;
import com.springboot.st.hotelProject.domain.dto.Hotel_RoomDto;
import com.springboot.st.signupProject.service.Room_Img_S3DBService;
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
    private final Room_Img_S3DBService room_img_s3DBService;
    private final HotelReservationAllDayService hotelReservationAllDayService;

    public List<Hotel_Room> all_find() {
        List<Hotel_Room> hotel_rooms = hotel_roomRepository.findAll();
        return hotel_rooms;
    }


    public Long save(Hotel_RoomDto hotel_roomDto, List<MultipartFile> multipartFile) {
        Long save_id = null;
        if (!multipartFile.isEmpty()) {
            try {
                List<Hotel_Room_Img> hotel_room_imgs = room_img_s3DBService.saveImg_S3(multipartFile);
                save_id = hotel_roomRepository.save(Hotel_RoomDto.create_room(hotel_roomDto, hotel_room_imgs)).getId();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            save_id = hotel_roomRepository.save(Hotel_RoomDto.create_room(hotel_roomDto)).getId();
        }

        return save_id;
    }

    public Hotel_Room find_By_Idx(String idx) {
        Long id = Long.parseLong(idx);
        return hotel_roomRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    //local
//    public void delete_Room(Hotel_RoomDto hotel_roomDto){
//        Hotel_Room hotel_room = find_By_Idx(hotel_roomDto.getId());
//        List<Hotel_Room_Img> hotel_room_img = hotel_room.getHotel_room_img();
//        hotel_roomRepository.delete(hotel_room);
//        if(!hotel_room_img.isEmpty()){
//
//            img_Delete(hotel_room_img);
//
//        }
//    }

    //aws Delete
    public void delete_Room(Hotel_RoomDto hotel_roomDto) {
        Hotel_Room hotel_room = find_By_Idx(hotel_roomDto.getId());
        List<Hotel_Room_Img> hotel_room_img = hotel_room.getHotel_room_img();
        hotel_roomRepository.delete(hotel_room);
        if (!hotel_room_img.isEmpty()) {
            room_img_s3DBService.delete_S3Img(hotel_room_img);
        }

    }

    public List<Hotel_Room> minPeopleGreaterThan(int people) {
        return hotel_roomRepository.findByMaxPeopleGreaterThanEqual(people);
    }

    public List<Hotel_Room> search_Room_for_day(List<Hotel_Room> nokoru_rooms, String checkin, String checkout) {
        List<Hotel_Room> hotel_rooms = new ArrayList<>();
        List<String> check_day_all = hotelReservationAllDayService.allDay_check(checkin, checkout);
        List<Hotel_Reservation_AllDay> reservation_allDays =
                hotelReservationAllDayService.find_all();
        int check_cnt = 0;
        for (Hotel_Room hotel_room : nokoru_rooms) {
            for(String check_day : check_day_all){
                if(hotel_day_check_room(hotel_room,check_day,reservation_allDays)){
                    check_cnt++;
                }
            }
            if(check_day_all.size()==check_cnt){
                hotel_rooms.add(hotel_room);
            }
            check_cnt=0;
        }
        return hotel_rooms;

    }

    private boolean hotel_day_check_room(Hotel_Room hotel_room, String day, List<Hotel_Reservation_AllDay> hotel_reservation_allDays){
        int cnt = 0;
        for(Hotel_Reservation_AllDay hotel_reservation_allDay : hotel_reservation_allDays){
            if(hotel_reservation_allDay.getRoomName().equals(hotel_room.getRoomName()) && hotel_reservation_allDay.getDay().equals(day)){
                cnt++;
            }
        }
        if(hotel_room.getRoomcount()> cnt){
            return true;
        }else{
            return false;
        }
    }


    public Hotel_Room find_room_name(String room_name) {
        return hotel_roomRepository.findHotel_RoomByRoomName(room_name);
    }

    public void update_Room(Hotel_RoomDto hotel_roomDto, List<MultipartFile> multipartFiles) throws IOException {
        Hotel_Room hotel_room = find_By_Idx(hotel_roomDto.getId());
        hotel_room.setMaxPeople(hotel_roomDto.getMaxpeople());
        hotel_room.setMinPeople(hotel_roomDto.getMinpeople());
        hotel_room.setPrice(hotel_roomDto.getPrice());
        hotel_room.setContent(hotel_roomDto.getContent());
        hotel_room.setRoomName(hotel_roomDto.getRoomname());
        hotel_room.setRoomcount(hotel_roomDto.getRoomcount());

        if (!multipartFiles.isEmpty()) {
            List<Hotel_Room_Img> hotel_room_imgs = hotel_room.getHotel_room_img();
            if (!hotel_room_imgs.isEmpty()) {
                hotel_room.setHotel_room_img(null);
                room_img_s3DBService.delete_S3Img(hotel_room_imgs);
                hotel_room.setHotel_room_img(room_img_s3DBService.saveImg_S3(multipartFiles));
            } else {
                hotel_room.setHotel_room_img(room_img_s3DBService.saveImg_S3(multipartFiles));
            }
        }
        hotel_roomRepository.save(hotel_room);
    }

    // local
//    private void img_Delete(List<Hotel_Room_Img> hotel_room_imgList){
//        for(Hotel_Room_Img hotel_room_img : hotel_room_imgList){
//            hotel_room_imgRepository.delete(hotel_room_img);
//            Path filePath = Paths.get(hotel_room_img.getImg_Server_Path());
//            try{
//                Files.delete(filePath);
//            }catch (NoSuchFileException e){
//                e.getStackTrace();
//            }catch (IOException e){
//                e.getStackTrace();
//            }
//
//
//        }
//    }
//local
//    private List<Hotel_Room_Img> img_Save(List<MultipartFile> multipartFile) throws IOException {
//        List<Hotel_Room_Img> hotel_room_imgs = new ArrayList<>();
//
//        for(MultipartFile multipartFile1 : multipartFile){
//
//            String uuid = UUID.randomUUID().toString();
//            Hotel_Room_Img save_Img = new Hotel_Room_Img();
//            String origin_name = multipartFile1.getOriginalFilename();
//            String server_name = uuid+origin_name;
//            String path = "src\\main\\resources\\static\\hotel\\assets\\img\\rooms";
//            File file = new File(path);
//            String absolute_Path = new File("").getAbsolutePath()+"\\";
//            if(!file.exists()){
//                file.mkdirs();
//            }
//            file = new File(absolute_Path+path+"/"+server_name);
//            multipartFile1.transferTo(file);
//
//            save_Img.setImg_UUID(uuid);
//            save_Img.setImg_Server_Name(server_name);
//            save_Img.setImg_Server_Path(file.getPath());
//            save_Img.setImg_Name(origin_name);
//            Hotel_Room_Img hotel_room_img1 =hotel_room_imgRepository.save(save_Img);
//
//            hotel_room_imgs.add(hotel_room_img1);
//        }
//
//        return hotel_room_imgs;
//
//    }


}
