package com.shoes101.service;

import com.shoes101.pojo.Admin;
import com.shoes101.pojo.User;

import javax.servlet.http.HttpServletResponse;

public interface AdminService {

    //登录验证
    public String login(HttpServletResponse response, Admin admin);
    public Admin getByToken(HttpServletResponse response, String token);
}
