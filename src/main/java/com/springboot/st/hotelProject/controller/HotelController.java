package com.springboot.st.hotelProject.controller;

import com.springboot.st.domain.pay.Payment;
import com.springboot.st.domain.user.User;
import com.springboot.st.domain.user.UserRepository;
import com.springboot.st.hotelProject.domain.Hotel_Board;
import com.springboot.st.hotelProject.domain.Hotel_BoardRepository;
import com.springboot.st.hotelProject.domain.Hotel_Reservation;
import com.springboot.st.hotelProject.domain.Hotel_Room;
import com.springboot.st.hotelProject.domain.dto.HotelBoardDto;
import com.springboot.st.hotelProject.domain.dto.HotelReservationDto;
import com.springboot.st.hotelProject.domain.dto.PaymentDto;
import com.springboot.st.hotelProject.service.*;
import com.springboot.st.signupProject.service.UserService;
import com.springboot.st.signupProject.web.dto.UserFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.ConditionalOnRepositoryType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class HotelController {

    private final UserService userService;

    private final HotelRoomService hotelRoomService;

    private final HotelReservationService hotelReservationService;

    private final UserRepository userRepository;

    private final HotelReservationAllDayService hotelReservationAllDayService;

    private final SMSService smsService;

    private final HotelBoardService hotelBoardService;

    private final PaymentService paymentService;

    private final Hotel_BoardRepository hotel_boardRepository;


    @GetMapping("/hotel/index")
    public String aaa(Model model) {
        List<Hotel_Room> hotel_room = hotelRoomService.all_find();
        model.addAttribute("hotelRoom",hotel_room);
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
        if(checkin.equals("") || checkout.equals("")){
            model.addAttribute("message","Please select the date");
            return ("hotel/index");
        }else{
            List<Hotel_Room> hotel_rooms_check_people = hotelRoomService.minPeopleGreaterThan(Integer.parseInt(people));
            List<Hotel_Room> hotel_rooms = hotelRoomService.search_Room_for_day(hotel_rooms_check_people,checkin,checkout);
            model.addAttribute("hotel_room", hotel_rooms);
            model.addAttribute("hotel_send", new Hotel_Room());
            model.addAttribute("reservation_checkin_date", checkin);
            model.addAttribute("reservation_checkout_date", checkout);
            model.addAttribute("people", people);
            return "hotel/search";
        }

    }

    @GetMapping("/hotel/reservation")
    public String reservation() {
        return "hotel/reservation";
    }


    @PostMapping("/hotel/reservation")
    public String reservation(Model model, @RequestParam Map<String, String> reservation_all) {
        User user = userService.get_user_for_userid(reservation_all.get("user_send"));
        Hotel_Room hotel_room = hotelRoomService.find_By_Idx(reservation_all.get("hotel_room_send"));
        List<String> all_day = hotelReservationAllDayService.allDay_check(reservation_all.get("checkin"),reservation_all.get("checkout"));
        String pee = String.valueOf(all_day.size()*hotel_room.getPrice());

        model.addAttribute("reservation", reservation_all);
        model.addAttribute("user", user);
        model.addAttribute("pee", pee);

        return "hotel/reservation";
    }

    //test
//    @PostMapping("/hotel/reservation/test")
//    public @ResponseBody
//    String
//    add_reservation(@RequestBody Map<String, Object> test) {
//        Hotel_Reservation hotel_reservation = hotelReservationService.save(test);
//        smsService.sms_Send(hotel_reservation);
//        return "予約成功しました";
//    }




    //bootpay test
//    @GetMapping("/hotel/aa")
//    public String aa(Model model) {
//        List<Hotel_Room> hotel_rooms = hotelRoomService.all_find();
//        model.addAttribute("test_hotel_room", hotel_rooms);
//        return "import_test";
//    }

    //payment submit
    @RequestMapping(value="/payment/submit" , method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody String payment_submit(@RequestBody Map<String,Object> request){
        Hotel_Reservation hotel_reservation = hotelReservationService.save(request);
        smsService.naverSmsSendService(hotel_reservation);
        return "OK";
    }

    @GetMapping("/smstest")
    public String aa(){

        return "/s3test";
    }

    //Contact
    @GetMapping("/hotel/contact")
    public String contact_aa(){
        return "hotel/contact";
    }


    @PostMapping("/smstestnaver")
    public @ResponseBody String test_sms(){
        Long id = 68L;
        Hotel_Reservation hotel_reservation = hotelReservationService.findById(id);
        System.out.println(hotel_reservation.toString());
        smsService.naverSmsSendService(hotel_reservation);
        return "OK";
    }


    //blog
    @GetMapping("/hotel/blog")
    public String blog(Model model, @PageableDefault(size = 5) Pageable pageable) {
        Page<Hotel_Board> hotel_board = hotelBoardService.find_all_board(pageable);


        model.addAttribute("hotel_board", hotel_board);
        model.addAttribute("hotel_board_count", hotel_board.getTotalElements());
        int startPage = Math.max(0, hotel_board.getPageable().getPageNumber() - 4);
        int endPage = Math.min(hotel_board.getTotalPages(), hotel_board.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "hotel/blog";

    }

    @GetMapping(value = "/hotel/blog/{id}")
    public String hotelDetail(Model model, @PathVariable("id") Long id){
        HotelBoardDto hotelBoardDto = hotelBoardService.hotelBoardDto(id);
        String userid = hotelBoardDto.getUser().getUserid();

        model.addAttribute("userid", userid);
        model.addAttribute("blog", hotelBoardDto);
        model.addAttribute("id", id);

        return "hotel/blog_details";
    }

    // blog write
    @GetMapping("/hotel/blog/write")
    public String blogWrite() { return "hotel/blog_insert"; }

    @PostMapping("/hotel/blog/write")
    public String blogForm(@RequestParam("title") String title, @RequestParam("content") String content, @RequestParam("attachment") MultipartFile file, @RequestParam("authId") Long userId) {

        System.out.println(userId);
        User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
        ;
        Hotel_Board hotel_board = hotelBoardService.save(file, title, content, user);
        return "redirect:/hotel/blog";


    }

    @GetMapping("/hotel/inquiry")
    public String inquiry(){
        return "hotel/inquiry";
    }

    //조회
    @GetMapping(value = "/hotel/inquiry/details/{receiptid}/{phone}")
    public String inquiryDetails(Model model,@PathVariable String receiptid, @PathVariable String phone){
        Payment payment = paymentService.findByReceiptIdToId(receiptid);
        HotelReservationDto hotelReservationDto = hotelReservationService.findByPaymentIdPhone(payment,phone);
        System.out.println(hotelReservationDto.toString());
        PaymentDto paymentDto = PaymentDto.of(payment);



        int totalPrice = Integer.parseInt(paymentDto.getPayPrice());
        int person = hotelReservationDto.getPeople();

        int price = totalPrice/person;

        model.addAttribute("invoice", hotelReservationDto);
        model.addAttribute("payment", paymentDto);
        model.addAttribute("price", price);

        return "hotel/inquiry_details";
    }

    @PostMapping(value = "/hotel/inquiry/details")
    public String inquiry(Model model, @RequestParam("userid") String userid, @RequestParam("phone1") String phone1, @RequestParam("phone2") String phone2, @RequestParam("phone3") String phone3){
        Long id = Long.parseLong(userid);
        String phone = phone1 + phone2 + phone3;
        HotelReservationDto hotelReservationDto = hotelReservationService.hotelReservationDto(id, phone);
        PaymentDto paymentDto = paymentService.paymentDto(hotelReservationDto.getPaymentId());

        int totalPrice = Integer.parseInt(paymentDto.getPayPrice());
        int person = hotelReservationDto.getPeople();

        int price = totalPrice/person;

        model.addAttribute("invoice", hotelReservationDto);
        model.addAttribute("payment", paymentDto);
        model.addAttribute("price", price);

        return "hotel/inquiry_details";
    }

    @PostMapping(value = "/hotel/blog/delete")
    public String blogDelete(@RequestParam("blogId") Long id){
        Hotel_Board hotel_board = hotel_boardRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        hotelBoardService.delete(hotel_board);
        return "redirect:/hotel/blog";
    }
}