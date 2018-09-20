package com.shoes101.mapper;

import com.shoes101.pojo.Shoessku;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShoesskuMapper {
    int deleteByPrimaryKey(Integer skuid);

    int insert(Shoessku record);

    Shoessku selectByPrimaryKey(Integer skuid);

    List<Shoessku> selectAll();

    int updateByPrimaryKey(Shoessku record);

    @Select("select sum(quantity) from shoessku where shoesid = #{shoesid}")
    int selectByshoesIdSum(@Param("shoesid")Integer shoesid);
}