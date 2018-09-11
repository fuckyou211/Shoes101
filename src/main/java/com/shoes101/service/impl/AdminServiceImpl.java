package com.shoes101.service.impl;


import com.shoes101.mapper.back.AdminMapper;
import com.shoes101.pojo.Admin;
import com.shoes101.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public Admin findAdmin(String adminName) {
        return adminMapper.findAdmin(adminName);
    }
}
