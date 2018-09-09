package com.shoes101.mapper;

import com.shoes101.config.mybatis.BaseMapper;
import com.shoes101.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    //登录验证
    @Select("select * from admin where adminname = #{adminName}")
    public Admin findAdmin(String adminName);


}
