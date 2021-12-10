package com.springboot.st.hotelProject.controller;

import com.springboot.st.config.auth.PrincipalDetails;
import com.springboot.st.domain.user.User;
import com.springboot.st.hotelProject.domain.Hotel_Reservation;
import com.springboot.st.hotelProject.domain.Hotel_Room;
import com.springboot.st.hotelProject.domain.dto.Hotel_RoomDto;
import com.springboot.st.hotelProject.service.HotelReservationService;
import com.springboot.st.hotelProject.service.HotelRoomService;
import com.springboot.st.signupProject.service.UserService;
import com.springboot.st.signupProject.web.dto.UserFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Controller
@RequiredArgsConstructor
public class HotelController {

    private final UserService userService;
    private final HotelRoomService hotelRoomService;
    private final HotelReservationService hotelReservationService;

    @GetMapping("/hotel/index")
    public String aaa() {
        return "hotel/index";
    }

    @GetMapping("/hotel/signup")
    public String signup(Model model) {
        model.addAttribute("userDto", new UserFormDto());
        return "hotel/signup";
    }

    @PostMapping("/hotel/signup")
    public String signup(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(userFormDto.getUserid());
            return "/hotel/signup";
        }
        userService.check_id_for_js(userFormDto.getUserid());

        try {
            userService.save(userFormDto);
        } catch (IllegalStateException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "hotel/signup";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/hotel/signin")
    public String signin() {
        return "hotel/signin";
    }

    @GetMapping("/hotel/rooms")
    public String rooms(Model model) {
        List<Hotel_Room> hotel_rooms = hotelRoomService.all_find();
        model.addAttribute("hotel_room", hotel_rooms);
        return "hotel/rooms";
    }

    @GetMapping("/hotel/search")
    public String search() {
        return "hotel/search";
    }

    @PostMapping("/hotel/search")
    public String search(Model model, @RequestParam("checkin_date") String checkin,
                         @RequestParam("checkout_date") String checkout, @RequestParam("select1") String people) {

        List<Hotel_Room> hotel_rooms_check_people = hotelRoomService.minPeopleGreaterThan(Integer.parseInt(people));
        List<Hotel_Room> hotel_rooms = hotelRoomService.search_Room_for_day(hotel_rooms_check_people,checkin,checkout);
        model.addAttribute("hotel_room", hotel_rooms);
        model.addAttribute("hotel_send", new Hotel_Room());
        model.addAttribute("reservation_checkin_date", checkin);
        model.addAttribute("reservation_checkout_date", checkout);
        model.addAttribute("people", people);
        return "hotel/search";
    }

    @GetMapping("/hotel/reservation")
    public String reservation() {
        return "hotel/reservation";
    }

    @PostMapping("/hotel/reservation")
    public String reservation(Model model, @RequestParam Map<String, String> reservation_all) {
        User user = userService.get_user_for_userid(reservation_all.get("user_send"));
        model.addAttribute("reservation", reservation_all);
        model.addAttribute("user", user);
        return "hotel/reservation";
    }

    @PostMapping("/hotel/reservation/test")
    public @ResponseBody
    String
    add_reservation(@RequestBody Map<String, Object> test) {
        Hotel_Reservation hotel_reservation = hotelReservationService.save(test);
        System.out.println(hotel_reservation.toString());
        return "予約成功しました";
    }


    //bootpay test
    @GetMapping("/hotel/aa")
    public String aa(Model model) {
        List<Hotel_Room> hotel_rooms = hotelRoomService.all_find();
        model.addAttribute("test_hotel_room", hotel_rooms);
        return "import_test";
    }

}
