package com.shoes101.mapper;

import com.shoes101.pojo.Demo;
import java.util.List;
//通过XML进行配置 mapper.xml
public interface DemoMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(Demo record);

    Demo selectByPrimaryKey(Integer did);

    List<Demo> selectAll();

    int updateByPrimaryKey(Demo record);
}