package com.springboot.st.web.shopCon;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.jws.WebParam;

@Controller(value = "/shop")
public class ShopController {

    @GetMapping(value = "/index")
    public String indexShop(Model model){
        model.addAttribute("hello", "joyoungwook");
        return "/shop/index";
    }

}
