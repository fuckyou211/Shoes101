package com.shoes101.mapper;

import com.shoes101.pojo.Orderdetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderdetailMapper {
    int deleteByPrimaryKey(Integer detailid);

    int insert(Orderdetail record);

    Orderdetail selectByPrimaryKey(Integer detailid);

    List<Orderdetail> selectAll();

    int updateByPrimaryKey(Orderdetail record);

    //根据订单返回订单详细值
    public List<Orderdetail> getOrderdetail(@Param("orderid") int orderid);

    //根据订单id删除订单详情
    public int deteleDetail(@Param("orderid") int orderid);

    //获取最新插入到shoesorder的id
    int getLateOrderId();



}