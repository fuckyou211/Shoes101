package com.shoes101.service;

import com.shoes101.pojo.Useraddress;

import java.util.List;

public interface UserInfoService {

    //根据用户id获取所有的收货地址
    public List<Useraddress> getUA(int userid);

    //增加收货地址 省市区
    public List<Useraddress> addUA(int userid,String province,String city,String detail);

    //增加收货地址 详细地址
    public List<Useraddress> addUA2(int userid,String address);

    //删除收货地址
    public List<Useraddress> deleteUA(int userid,String address);

    //更新收货地址 省市区
    public List<Useraddress> updateUA(int userid, String province, String city, String detail);

    //更新收货地址 详细地址
    public List<Useraddress> updateUA2(int userid,String address);

}
