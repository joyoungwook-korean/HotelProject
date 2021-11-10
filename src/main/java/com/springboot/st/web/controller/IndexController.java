package com.springboot.st.web.controller;


import com.springboot.st.config.auth.PrincipalDetails;
import com.springboot.st.signupProject.web.dto.UserFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final HttpSession httpSession;


    @GetMapping("/user")
    public @ResponseBody
    String testuser(@AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println(principalDetails.getAttributes());
        System.out.println(principalDetails.getUsername());
        return "1234";
    }

    @GetMapping("/")
    public  String index(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model){

        if(principalDetails!=null){
            model.addAttribute("userDto",principalDetails);
        }
        return "index";
        /*
        //CustiomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser을 저장하도록 구성
        // 즉 성공 시 httpSession.getAttribute("user")에 있는 값을 가져올 수 있음
        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        //세션에 값이 있을때만 userName에 등록
        if(user!=null){
            model.addAttribute("userName",user.getName());
        }
        */
    }


}
