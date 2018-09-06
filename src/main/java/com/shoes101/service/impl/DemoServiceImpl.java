package com.shoes101.service.impl;

import com.shoes101.mapper.DemoMapper;
import com.shoes101.mapper.UserMapper;
import com.shoes101.pojo.User;
import com.shoes101.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//仅用于测试
@Service
public class DemoServiceImpl implements DemoService {

    //测试通用的Mapper
    @Autowired
    DemoMapper demoMapper;

    @Autowired
    UserMapper userMapper;

    @Override
    public int test() {
        return 0;
    }

    @Override
    public int insertUser(User user) {

        return userMapper.insert(user);
    }
}
