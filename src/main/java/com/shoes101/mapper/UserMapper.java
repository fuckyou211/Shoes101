package com.shoes101.mapper;

import com.shoes101.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    User selectByPrimaryKey(Integer userid);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    //冻结用户
    public int getCold(@Param("userid") int userid,@Param("cold") int cold);

    //解冻用户
    public int getWarm(@Param("userid") int userid,@Param("cold") int cold);

    //新增用户
    int insertUser(User user);

    @Select("select * from user where phone=#{phone}")
    public User getByMobile(@Param("phone") String phone);
}