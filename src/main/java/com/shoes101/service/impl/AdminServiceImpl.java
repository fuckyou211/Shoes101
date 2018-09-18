package com.shoes101.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.shoes101.mapper.back.AdminMapper;
import com.shoes101.pojo.Admin;
import com.shoes101.service.AdminService;
import com.shoes101.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public String findAdmin(String adminName,String password) {
        //二次加密
        String endPassword = MD5Util.formPassToDBPass(password,MD5Util.getSalt());
        Admin admin = adminMapper.findAdmin(adminName);
        if(admin == null)
        {
            return JSONObject.toJSONString("error");
        }
        else if (admin.getPassword().equals(endPassword))
        {
            return JSONObject.toJSONString("yes");
        }
        return JSONObject.toJSONString("error");

    }
}
