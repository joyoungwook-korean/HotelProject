package com.springboot.st.signupProject.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ManagerController {

    @GetMapping(value = "manager")
    public String manager_index(){

        return "admin/index_manager";
    }

}
