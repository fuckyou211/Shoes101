package com.shoes101.service;

import com.shoes101.pojo.Admin;

public interface AdminService {

    //登录验证
    public int findAdmin(String adminName,String password);
}
