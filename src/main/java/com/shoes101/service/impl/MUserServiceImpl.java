package com.shoes101.service.impl;

import com.shoes101.mapper.UserMapper;
import com.shoes101.pojo.User;
import com.shoes101.service.MUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MUserServiceImpl implements MUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUser() {
        return userMapper.selectAll();
    }

    @Override
    public List<User> getCold(int userid) {
        int result = userMapper.getCold(userid);
        System.out.println(result);
        return userMapper.selectAll();
    }
}
