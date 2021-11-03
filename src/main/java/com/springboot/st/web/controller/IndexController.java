package com.springboot.st.web.controller;

import com.springboot.st.service.posts.PostsService;
import com.springboot.st.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;
    private final HttpSession httpSession;


    @GetMapping("/")
    public  String index(Model model){
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

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post",dto);
        return "posts-update";
    }
}
