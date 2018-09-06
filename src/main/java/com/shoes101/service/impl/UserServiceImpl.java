package com.shoes101.service.impl;

import com.shoes101.mapper.UserMapper;
import com.shoes101.pojo.User;
import com.shoes101.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;//这里会报错，但是并不会影响

    @Override
    public int addUser(User user) {
        System.out.println(user.getUserName());
        System.out.println(user.getUserId());
        System.out.println(user.getPhone());
        return userMapper.insert(user);
    }

//    @Override
//    public User getUser()
//    {
//        return userMapper.getUser();
//    }
}
