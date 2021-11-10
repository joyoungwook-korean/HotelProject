package com.springboot.st.hotelProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/hotel")
@Controller
public class HotelController {


    @GetMapping("/index")
    public String aaa(){
        return "hotel/index";
    }

    @GetMapping("/signup")
    public String signup(){
        return "hotel/signup";
    }

}
