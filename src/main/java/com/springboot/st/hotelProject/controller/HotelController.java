package com.springboot.st.hotelProject.controller;

import com.springboot.st.signupProject.service.UserService;
import com.springboot.st.signupProject.web.dto.UserFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
public class HotelController {

    private final UserService userService;

    @GetMapping("/hotel/index")
    public String aaa(){
        return "hotel/index";
    }

    @GetMapping("/hotel/signup")
    public String signup(Model model){
        model.addAttribute("userDto", new UserFormDto());
        return "hotel/signup";
    }

    @PostMapping("/hotel/signup")
    public String signup(@Valid UserFormDto userFormDto, BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
          System.out.println(userFormDto.getUserid());
            return "/hotel/signup";
        }
        userService.check_id_for_js(userFormDto.getUserid());

        try{
            userService.save(userFormDto);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "hotel/signup";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/hotel/signin")
    public String signin(){
        return "hotel/signin";
    }

    @GetMapping("/hotel/rooms")
    public String rooms(){
        return "hotel/rooms";
    }

}
