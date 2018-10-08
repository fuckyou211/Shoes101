package com.shoes101.mapper;

import com.shoes101.pojo.Useraddress;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UseraddressMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Useraddress record);

    Useraddress selectByPrimaryKey(Integer userid);

    List<Useraddress> selectAll();

    int updateByPrimaryKey(Useraddress record);

    //删除收货地址
    int deleteUA(@Param("userid") int userid, @Param("address") String address);

    //根据用户id获取所有收货地址
    public List<Useraddress> getUA(@Param("userid") int userid);

}