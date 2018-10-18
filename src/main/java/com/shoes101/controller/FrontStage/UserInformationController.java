package com.shoes101.controller.FrontStage;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/UserInformation")
public class UserInformationController {

    private  final Logger logger= LoggerFactory.getLogger(UserInformationController.class) ;


    @RequestMapping("/UserMyAccount")
    public String UserMyAccount(HttpServletRequest request, Model model, User user)
    {
        logger.info("user:111111111111111111111111111");
        logger.info("user:"+JSONObject.toJSONString(user));
        return "front/my-account";
    }



}
