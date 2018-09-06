package com.shoes101.controller;

import com.shoes101.pojo.Admin;
import com.shoes101.service.AdminService;
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

    @RequestMapping("/login")
    public String login()
    {
        return "login";
    }


    @RequestMapping("/doLogin")
    @ResponseBody
    public String doLogin(HttpServletResponse response, @RequestParam(value="adminName") String adminName,
                          @RequestParam(value="password") String password)
    {
        //String data = "";
        System.out.println("aaaa");
        Admin admin = adminservice.findAdmin(adminName);
        System.out.println(admin.getPassword());
        System.out.println(admin.getAdminName());
        if(admin == null)
        {
            //data = "error";
            System.out.println("1");
            return "error";
        }
        else if (admin.getPassword().equals(password))
        {
            //data = "yes";
            System.out.println("2");
            return "";
        }
       // data = "error";
        System.out.println("3");
        return "error";
    }


}
