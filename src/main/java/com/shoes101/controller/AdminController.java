package com.shoes101.controller;

import com.shoes101.pojo.Admin;
import com.shoes101.result.Result;
import com.shoes101.service.AdminService;
import com.shoes101.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台管理总控制器 实现功能跳转
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminservice;

    @Autowired
    private PropertyService propertyService;


    /**
     *  跳转到后台管理员界面
     */

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }

    /**
     *  登录成功跳转到后台管理
     */

    @RequestMapping("/toback")
    public String toback()
    {
        return "/back/index";
    }



    //Add添加鞋
    @RequestMapping("/addShoes")
    public String addShoes()
    {
        return "/back/addShoes";
    }


    //登录测试样例
    @RequestMapping("/result")
    public String result()
    {
        return "/back/result";
    }


    /**
     * 登录
     * @param response
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, Admin admin)
    {
        return Result.success(adminservice.login(response,admin));
    }

}
