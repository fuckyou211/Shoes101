package com.shoes101.mapper;

import com.shoes101.pojo.Shoesorder;
import java.util.List;

public interface ShoesorderMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(Shoesorder record);

    Shoesorder selectByPrimaryKey(Integer orderid);

    List<Shoesorder> selectAll();

    int updateByPrimaryKey(Shoesorder record);
}