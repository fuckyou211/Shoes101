package com.shoes101.mapper;

import com.shoes101.pojo.Shoescatalog;
import java.util.List;

public interface ShoescatalogMapper {
    int deleteByPrimaryKey(Integer catalogid);

    int insert(Shoescatalog record);

    Shoescatalog selectByPrimaryKey(Integer catalogid);

    List<Shoescatalog> selectAll();

    int updateByPrimaryKey(Shoescatalog record);
}