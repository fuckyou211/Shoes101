package com.shoes101.controller;

import com.alibaba.fastjson.JSON;
import com.shoes101.pojo.Property;
import com.shoes101.pojo.Shoescatalog;
import com.shoes101.service.AdminService;
import com.shoes101.service.CatalogService;
import com.shoes101.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * 跳转到商品管理
     */

    @RequestMapping("/toshoes")
    public String toshoes()
    {
        return "back/manager_shoes";
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
     * @param adminName
     * @param password
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public Object doLogin(HttpServletResponse response, @RequestParam(value="adminName") String adminName,
                          @RequestParam(value="password") String password)
    {
        return adminservice.findAdmin(adminName,password);
    }







}
