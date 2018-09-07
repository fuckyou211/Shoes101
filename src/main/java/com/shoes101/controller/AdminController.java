package com.shoes101.controller;

import com.shoes101.pojo.Admin;
import com.shoes101.service.AdminService;
import com.shoes101.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminservice;
    //跳转到后台管理员界面
    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }
    //登录测试样例
    @RequestMapping("/result")
    public String result()
    {
        return "result";
    }

    // 登录
    @RequestMapping("/doLogin")
    @ResponseBody
    public Object doLogin(HttpServletResponse response, @RequestParam(value="adminName") String adminName,
                          @RequestParam(value="password") String password)
    {
        //String data = "";
        //System.out.println("aaaa");
        //MD5Util md5Util = new MD5Util();

        System.out.println(password);

        //首次加密
        String secondPass = MD5Util.inputPassToFormPass(password);

        System.out.println(secondPass);
        //二次加密
        // String endPassword = MD5Util.formPassToDBPass(secondPass,MD5Util.getSalt());

        //System.out.println(endPassword);
        //加密的密码在数据库找对应
        Admin admin = adminservice.findAdmin(adminName);
        //System.out.println(admin.getPassword());
        //System.out.println(admin.getAdminName());
        if(admin == null)
        {
            //data = "error";
            //System.out.println("1");
            return "0";
        }
        else if (admin.getPassword().equals(secondPass))
        {
            //data = "yes";
            //System.out.println("2");
            return "1";
        }
       // data = "error";
        //System.out.println("3");
        return "0";
    }


}
