package com.shoes101.controller.FrontStage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class HomePageController {

    @RequestMapping("/ShoesShop/index")
    public String toHomePage(){
        return"/front/index";
    }
}
