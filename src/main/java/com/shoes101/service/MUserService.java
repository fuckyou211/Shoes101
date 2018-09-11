package com.shoes101.service;

import com.shoes101.pojo.User;

import java.util.List;

public interface MUserService {

    //获取所有用户
    public List<User> getAllUser();

    //冻结用户
    public List<User> getCold(int userid);


}
