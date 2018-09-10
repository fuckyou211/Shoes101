package com.shoes101.mapper;

import com.shoes101.pojo.Orderdetail;
import java.util.List;

public interface OrderdetailMapper {
    int deleteByPrimaryKey(Integer detailid);

    int insert(Orderdetail record);

    Orderdetail selectByPrimaryKey(Integer detailid);

    List<Orderdetail> selectAll();

    int updateByPrimaryKey(Orderdetail record);
}