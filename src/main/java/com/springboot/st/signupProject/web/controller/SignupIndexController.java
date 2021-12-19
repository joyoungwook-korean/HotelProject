package com.springboot.st.signupProject.web.controller;



import com.springboot.st.signupProject.service.UserService;
import com.springboot.st.signupProject.web.dto.UserFormDto;

import lombok.RequiredArgsConstructor;
import org.mariadb.jdbc.internal.logging.Logger;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class SignupIndexController {


    private final UserService userService;

    @GetMapping("/signup")
    public String signup_index(){
        return "signup/signup_index";
    }

    @GetMapping("/signup/signupForm")
    public String signupForm(Model model){
        model.addAttribute("userFormDto", new UserFormDto());
        return "signup/signup_Joinform";
    }

    @PostMapping("/signup/joinform")
    public String join(@Valid UserFormDto userFormDto, BindingResult bindingResult, Model model){



        if(bindingResult.hasErrors()){

            return "signup/signup_Joinform";
        }
        userService.check_id_for_js(userFormDto.getUserid());

        try{
            userService.save(userFormDto);
        } catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "signup/signup_Joinform";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/signup/signupForm/check")
    public @ResponseBody String ckeck(@RequestBody String userid){
        try{
            return userService.check_id_for_js(userid);
        }catch (HttpMessageNotReadableException e){
            return "0";
        }


    }
}
