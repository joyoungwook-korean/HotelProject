package com.springboot.st.signupProject.web.controller;

import com.springboot.st.domain.user.User;
import com.springboot.st.domain.user.UserRepository;
import com.springboot.st.hotelProject.domain.Hotel_Room;
import com.springboot.st.hotelProject.domain.dto.Hotel_RoomDto;
import com.springboot.st.hotelProject.service.HotelRoomService;
import com.springboot.st.signupProject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final HotelRoomService hotelRoomService;


    @GetMapping("/admin/hotel_crud")
    public String hotel_crud(Model model){
        List<Hotel_Room> hotel_rooms = hotelRoomService.all_find();
        model.addAttribute("hotelroom", hotel_rooms);
        model.addAttribute("hotelroomCount", hotel_rooms.size());
        model.addAttribute("hotelroomDto", new Hotel_RoomDto());
        return "admin/hotel_crud";
    }

    @PostMapping("/admin/hotel_crud")
    public String hotel_crud(@Valid @ModelAttribute("hotelroomDto")  Hotel_RoomDto hotel_roomDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            List<Hotel_Room> hotel_rooms = hotelRoomService.all_find();
            model.addAttribute("hotelroom", hotel_rooms);
            model.addAttribute("hotelroomCount", hotel_rooms.size());
            return "admin/hotel_crud";
        }

        hotelRoomService.save(hotel_roomDto);

        return "redirect:/admin/hotel_crud";
    }

    @GetMapping("/admin")
    public String admin_index( Model model){
        List<User> findAllUser = adminService.findAllUser();
        model.addAttribute("userDto", findAllUser);
        model.addAttribute("userCount",findAllUser.size());
        return "admin/index";
    }

    @PostMapping("/admin")
    public @ResponseBody List<User> post_index(@RequestBody String find_id, Model model){
        if(find_id==null){
            System.out.println("aa");
            return null;
        }else if(find_id.equals("")){

            System.out.println("bb");
            return null;
        }
        else{
            List<User> find_user_e = adminService.find_user_ajax(find_id);
            model.addAttribute("userDto",find_user_e);
            return find_user_e;
        }
    }
}
