package com.shoes101.mapper;

import com.shoes101.pojo.Splink;
import java.util.List;

public interface SplinkMapper {
    int deleteByPrimaryKey(Integer spid);

    int insert(Splink record);

    Splink selectByPrimaryKey(Integer spid);

    List<Splink> selectAll();

    int updateByPrimaryKey(Splink record);
}