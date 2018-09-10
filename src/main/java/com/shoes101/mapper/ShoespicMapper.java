package com.shoes101.mapper;

import com.shoes101.pojo.Shoespic;
import java.util.List;

public interface ShoespicMapper {
    int deleteByPrimaryKey(Integer picid);

    int insert(Shoespic record);

    Shoespic selectByPrimaryKey(Integer picid);

    List<Shoespic> selectAll();

    int updateByPrimaryKey(Shoespic record);
}