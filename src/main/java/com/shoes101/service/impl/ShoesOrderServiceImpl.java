package com.shoes101.service.impl;

import com.shoes101.mapper.OrderdetailMapper;
import com.shoes101.mapper.ShoesorderMapper;
import com.shoes101.pojo.Orderdetail;
import com.shoes101.pojo.Shoesorder;
import com.shoes101.service.ShoesOrderService;
import com.shoes101.vo.ShoesorderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoesOrderServiceImpl implements ShoesOrderService {

    @Autowired
    private ShoesorderMapper shoesorderMapper;

    @Autowired
    private OrderdetailMapper orderdetailMapper;

    public List<ShoesorderVo> getAllOrder()
    {
        List<ShoesorderVo> list = shoesorderMapper.selectAll();
        for(int i = 0;i < list.size();i++)
        {
            System.out.println(list.get(i).getBuydate());
        }

        return shoesorderMapper.selectAll();
    }

    public List<Orderdetail> getOrderdetail(int orderid)
    {
        return orderdetailMapper.getOrderdetail(orderid);
    }

    public List<ShoesorderVo> changeState(int state,int orderid)
    {
        int result = shoesorderMapper.changeState(state, orderid);
        System.out.println("修改状态:" + result);
        return shoesorderMapper.selectAll();
    }

    public List<ShoesorderVo> iscancel(int orderid,int cancel)
    {
        int result = shoesorderMapper.iscancel(orderid, cancel);
        System.out.println("退货状态:" + result);
        return shoesorderMapper.selectAll();
    }

    public List<ShoesorderVo> deleteOrder(int orderid)
    {
        int result0 = shoesorderMapper.deleteByPrimaryKey(orderid);
        int result1 = orderdetailMapper.deteleDetail(orderid);
        System.out.println("删除状态：:" + result0  + "删除订单详情状态：" + result1);
        return shoesorderMapper.selectAll();
    }

    public List<ShoesorderVo> getbyitem(int validity,int cancel, int status)
    {
        return shoesorderMapper.getbyitem(validity,cancel,status);
    }

    public List<ShoesorderVo> sendOrBack(int orderid,int validity, int cancel,int state)
    {
        shoesorderMapper.sendOrBack(orderid,validity,cancel,state);
        return shoesorderMapper.selectAll();
    }

    @Override
    public Shoesorder getOrderById(Integer orderId) {
        return shoesorderMapper.selectByPrimaryKey(orderId);
    }
}
