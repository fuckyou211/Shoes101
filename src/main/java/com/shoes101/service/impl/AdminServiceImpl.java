package com.shoes101.service.impl;


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
    public int findAdmin(String adminName,String password) {
        //二次加密
        String endPassword = MD5Util.formPassToDBPass(password,MD5Util.getSalt());
        Admin admin = adminMapper.findAdmin(adminName);
        if(admin == null)
        {
            //data = "error";
            //System.out.println("1");
            return 0;
        }
        else if (admin.getPassword().equals(endPassword))
        {
            //data = "yes";
            //System.out.println("2");
            return 1;
        }
        return 0;
    }
}
