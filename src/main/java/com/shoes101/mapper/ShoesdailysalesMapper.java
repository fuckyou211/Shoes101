package com.shoes101.mapper;

import com.shoes101.pojo.Shoesdailysales;
import java.util.List;

public interface ShoesdailysalesMapper {
    int deleteByPrimaryKey(Integer sdid);

    int insert(Shoesdailysales record);

    Shoesdailysales selectByPrimaryKey(Integer sdid);

    List<Shoesdailysales> selectAll();

    int updateByPrimaryKey(Shoesdailysales record);
}