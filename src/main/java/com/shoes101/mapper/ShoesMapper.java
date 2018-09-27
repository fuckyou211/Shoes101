package com.shoes101.mapper;

import com.shoes101.pojo.Shoes;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ShoesMapper {
    int deleteByPrimaryKey(Integer shoesid);

    int insert(Shoes record);

    Shoes selectByPrimaryKey(Integer shoesid);

    List<Shoes> selectAll();

    int updateByPrimaryKey(Shoes record);

    //获取所有鞋类
    public List getAllShoes();

    @Update("update  shoes set isdropoff=#{isdropoff} where shoesid=#{shoesid}")
    int selectupdateisdropoff(@Param("shoesid") Integer shoesid, @Param("isdropoff") String isdropoff);

}