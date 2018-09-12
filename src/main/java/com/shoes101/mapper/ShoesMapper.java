package com.shoes101.mapper;

import com.shoes101.pojo.Shoes;
import java.util.List;

public interface ShoesMapper {
    int deleteByPrimaryKey(Integer shoesid);

    int insert(Shoes record);

    Shoes selectByPrimaryKey(Integer shoesid);

    List<Shoes> selectAll();

    int updateByPrimaryKey(Shoes record);

    //获取所有鞋类
    public List getAllShoes();
}