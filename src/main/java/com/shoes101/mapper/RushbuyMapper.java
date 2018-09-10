package com.shoes101.mapper;

import com.shoes101.pojo.Rushbuy;
import java.util.List;

public interface RushbuyMapper {
    int deleteByPrimaryKey(Integer rushbuyid);

    int insert(Rushbuy record);

    Rushbuy selectByPrimaryKey(Integer rushbuyid);

    List<Rushbuy> selectAll();

    int updateByPrimaryKey(Rushbuy record);
}