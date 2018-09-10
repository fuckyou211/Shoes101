package com.shoes101.mapper;

import com.shoes101.pojo.Colorpic;
import java.util.List;

public interface ColorpicMapper {
    int deleteByPrimaryKey(Integer colorpicid);

    int insert(Colorpic record);

    Colorpic selectByPrimaryKey(Integer colorpicid);

    List<Colorpic> selectAll();

    int updateByPrimaryKey(Colorpic record);
}