package com.springboot.st.signupProject.web.controller;

import com.springboot.st.domain.user.User;
import com.springboot.st.domain.user.UserRepository;
import com.springboot.st.signupProject.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.jws.WebParam;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;





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
