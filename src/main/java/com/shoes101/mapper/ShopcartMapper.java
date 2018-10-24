package com.shoes101.mapper;

import com.shoes101.pojo.Shopcart;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

public interface ShopcartMapper {
    int deleteByPrimaryKey(Integer scid);

    int insert(Shopcart record);

    Shopcart selectByPrimaryKey(Integer scid);

    List<Shopcart> selectAll();

    int updateByPrimaryKey(Shopcart record);

    @Select("select * from shopcart where userid=#{userid}")
    Shopcart getShopCartByUser(@Param("userid") int userid);
}