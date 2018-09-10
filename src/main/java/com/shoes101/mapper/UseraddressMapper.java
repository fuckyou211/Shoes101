package com.shoes101.mapper;

import com.shoes101.pojo.Useraddress;
import java.util.List;

public interface UseraddressMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(Useraddress record);

    Useraddress selectByPrimaryKey(Integer userid);

    List<Useraddress> selectAll();

    int updateByPrimaryKey(Useraddress record);
}