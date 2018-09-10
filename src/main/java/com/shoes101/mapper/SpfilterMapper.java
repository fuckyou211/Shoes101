package com.shoes101.mapper;

import com.shoes101.pojo.Spfilter;
import java.util.List;

public interface SpfilterMapper {
    int insert(Spfilter record);

    List<Spfilter> selectAll();
}