package com.shoes101.service;

import com.shoes101.pojo.Orderdetail;
import com.shoes101.pojo.Shoesorder;
import com.shoes101.vo.ShoesorderVo;

import java.util.List;

public interface ShoesOrderService {

    //查询所有订单
    public List<ShoesorderVo> getAllOrder();

    //根据订单id返回详细值
    public List<Orderdetail> getOrderdetail(int orderid);

    //修改订单状态
    public List<ShoesorderVo> changeState(int state,int orderid);

    //申请退货
    public List<ShoesorderVo> iscancel(int orderid,int cancel);

    //删除订单
    public List<ShoesorderVo> deleteOrder(int orderid);

    //根据条件返回状态订单列表
    public List<ShoesorderVo> getbyitem(int validity,int cancel, int status);

    //发货或者退款
    public List<ShoesorderVo> sendOrBack(int orderid,int validity, int cancel,int state);

	/**
	 * 	根据 订单id获取订单
	 * @param orderId
	 * @return
	 */
	public Shoesorder getOrderById(Integer orderId);

}
