package com.shoes101.mapper;

import com.shoes101.pojo.Shopcart;
import java.util.List;

public interface ShopcartMapper {
    int deleteByPrimaryKey(Integer scid);

    int insert(Shopcart record);

    Shopcart selectByPrimaryKey(Integer scid);

    List<Shopcart> selectAll();

    int updateByPrimaryKey(Shopcart record);
}