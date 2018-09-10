package com.shoes101.mapper;

import com.shoes101.pojo.Shoessku;
import java.util.List;

public interface ShoesskuMapper {
    int deleteByPrimaryKey(Integer skuid);

    int insert(Shoessku record);

    Shoessku selectByPrimaryKey(Integer skuid);

    List<Shoessku> selectAll();

    int updateByPrimaryKey(Shoessku record);
}