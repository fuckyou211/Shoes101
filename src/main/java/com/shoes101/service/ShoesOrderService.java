package com.shoes101.service;

import com.shoes101.pojo.Orderdetail;
import com.shoes101.pojo.Shoesorder;

import java.util.List;

public interface ShoesOrderService {

    //查询所有订单
    public List<Shoesorder> getAllOrder();

    //根据订单id返回详细值
    public Orderdetail getOrderdetail(int orderid);

    //修改订单状态
    public List<Shoesorder> changeState(int state,int orderid);

    //申请退货
    public List<Shoesorder> iscancel(int orderid,int cancel);

    //删除订单
    public List<Shoesorder> deleteOrder(int orderid);

}
