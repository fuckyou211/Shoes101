package com.shoes101.mapper;

import com.shoes101.pojo.Shopcartdetails;
import java.util.List;

public interface ShopcartdetailsMapper {
    int deleteByPrimaryKey(Integer scdid);

    int insert(Shopcartdetails record);

    Shopcartdetails selectByPrimaryKey(Integer scdid);

    List<Shopcartdetails> selectAll();

    int updateByPrimaryKey(Shopcartdetails record);
}