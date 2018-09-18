package com.shoes101.service;

import com.shoes101.pojo.Admin;

public interface AdminService {

    //登录验证
    public String findAdmin(String adminName,String password);
}
