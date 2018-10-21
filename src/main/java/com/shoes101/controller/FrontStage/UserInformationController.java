package com.shoes101.controller.FrontStage;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.pojo.User;
import com.shoes101.result.Result;
import com.shoes101.service.UserInformationServicer;
import com.shoes101.service.impl.UserInformationServicerImpl;
import com.shoes101.vo.LoginCodeVo;
import com.shoes101.vo.UserImformationVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    /**
     * 更新信息
     * @param request
     * @param user
     * @param userImformationVo
     * @return
     */
    @RequestMapping("/UpdateInformation")
    @ResponseBody
    public Result<String> UpdateInformation(HttpServletRequest request,User user, @Valid UserImformationVo userImformationVo)
    {
        logger.info("userImformationVo:"+JSONObject.toJSONString(userImformationVo));
        userInformationServicerImpl.UpdateInformation(request,user,userImformationVo);
        return Result.success("信息修改成功！");
    }

    /**
     * 更新密码
     * @param request
     * @param user
     * @param——password原密码
     * @param——setpassword新密码
     * @return
     */
    @RequestMapping("/UpdatePassword")
    @ResponseBody
    public Result<String> UpdatePassword(HttpServletRequest request, User user, @RequestParam("password") String password,@RequestParam("setpassword") String setpassword)
    {
        logger.info("user:"+JSONObject.toJSONString(user));
        userInformationServicerImpl.UpdatePassword(request,user,password,setpassword);
        return Result.success("信息修改成功！");
    }



}
