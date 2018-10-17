package com.shoes101.service;

import com.shoes101.vo.OrderVo;

public interface OrderService {

    //下订单 此时已经登录
    //10.17 修改库存
    public String add(OrderVo orderItem);

    //查询订单
    public String check(int userid);

    //查询订单详情
    public String checkOrderDetail(int orderid);

    //获取用户的所有地址
    public String getAddress(int userid);
}
