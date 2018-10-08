package com.shoes101.service.impl;

import com.shoes101.mapper.UseraddressMapper;
import com.shoes101.pojo.Useraddress;
import com.shoes101.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UseraddressMapper useraddressMapper;

    //根据用户id获取所有收货地址
    public List<Useraddress> getUA(int userid)
    {
        return useraddressMapper.getUA(userid);
    }

    //增加收货地址 省市区+详细地址
    public List<Useraddress> addUA(int userid, String province, String city, String detail)
    {
        Useraddress ua = new Useraddress();
        String address = province + "" + city + "" + detail;
        ua.setAddress(address);
        ua.setUserid(userid);
        useraddressMapper.insert(ua);
        return useraddressMapper.getUA(userid);
    }

    //增加收货地址 详细地址
    public List<Useraddress> addUA2(int userid,String address)
    {
        Useraddress ua = new Useraddress();
        ua.setAddress(address);
        ua.setUserid(userid);
        useraddressMapper.insert(ua);
        return useraddressMapper.getUA(userid);
    }

    //删除收货地址
    public List<Useraddress> deleteUA(int userid,String address)
    {
        useraddressMapper.deleteUA(userid,address);
        return useraddressMapper.getUA(userid);
    }

    //更新收货地址 省市区
    public List<Useraddress> updateUA(int userid, String province, String city, String detail)
    {
        String address = province + "" + city + "" + detail;
        Useraddress ua = new Useraddress();
        ua.setAddress(address);
        ua.setUserid(userid);
        useraddressMapper.updateByPrimaryKey(ua);
        return useraddressMapper.getUA(userid);
    }

    //更新收货地址 详细地址
    public List<Useraddress> updateUA2(int userid,String address)
    {
        Useraddress ua = new Useraddress();
        ua.setAddress(address);
        ua.setUserid(userid);
        useraddressMapper.updateByPrimaryKey(ua);
        return useraddressMapper.getUA(userid);
    }

}
