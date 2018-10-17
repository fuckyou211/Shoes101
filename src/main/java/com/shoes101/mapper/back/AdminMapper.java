package com.shoes101.mapper.back;

import com.shoes101.config.mybatis.BaseMapper;
import com.shoes101.pojo.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    //登录验证
    @Select("select * from admin where adminname = #{adminName}")
    public Admin findAdmin(String adminName);

    //拿全部管理员
    @Select("select * from admin")
    public List<Admin> fingAllAdmin();
}
