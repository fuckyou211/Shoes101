package com.shoes101.controller.FrontStage;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.pojo.User;
import com.shoes101.result.Result;
import com.shoes101.service.UserInformationServicer;
import com.shoes101.service.impl.UserInformationServicerImpl;
import com.shoes101.vo.LoginCodeVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/UserInformation")
public class UserInformationController {

    private  final Logger logger= LoggerFactory.getLogger(UserInformationController.class) ;

    @Autowired
    private UserInformationServicer userInformationServicerImpl;

    @RequestMapping("/UserMyAccount")
    public String UserMyAccount(HttpServletRequest request, Model model, User user)
    {
        logger.info("user:"+JSONObject.toJSONString(user));
        model.addAttribute("imformation",userInformationServicerImpl.UserMyAccount(request,user));
        return "front/my-account";
    }

    @RequestMapping("/UpdateInformation")
    @ResponseBody
    public Result<String> UpdateInformation(HttpServletRequest request, @Valid LoginCodeVo loginCodeVo)
    {
        logger.info("user:"+JSONObject.toJSONString(loginCodeVo));

        return Result.success("信息修改成功！");
    }



}
