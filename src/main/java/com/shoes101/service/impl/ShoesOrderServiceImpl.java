package com.shoes101.service.impl;

import com.shoes101.mapper.OrderdetailMapper;
import com.shoes101.mapper.ShoesorderMapper;
import com.shoes101.pojo.Orderdetail;
import com.shoes101.pojo.Shoesorder;
import com.shoes101.service.ShoesOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoesOrderServiceImpl implements ShoesOrderService {

    @Autowired
    private ShoesorderMapper shoesorderMapper;

    @Autowired
    private OrderdetailMapper orderdetailMapper;

    public List<Shoesorder> getAllOrder()
    {
        return shoesorderMapper.selectAll();
    }

    public List<Orderdetail> getOrderdetail(int orderid)
    {
        return orderdetailMapper.getOrderdetail(orderid);
    }

    public List<Shoesorder> changeState(int state,int orderid)
    {
        int result = shoesorderMapper.changeState(state, orderid);
        System.out.println("修改状态:" + result);
        return shoesorderMapper.selectAll();
    }

    public List<Shoesorder> iscancel(int orderid,int cancel)
    {
        int result = shoesorderMapper.iscancel(orderid, cancel);
        System.out.println("退货状态:" + result);
        return shoesorderMapper.selectAll();
    }

    public List<Shoesorder> deleteOrder(int orderid)
    {
        int result0 = shoesorderMapper.deleteByPrimaryKey(orderid);
        int result1 = orderdetailMapper.deteleDetail(orderid);
        System.out.println("删除状态：:" + result0  + "删除订单详情状态：" + result1);
        return shoesorderMapper.selectAll();
    }
}
