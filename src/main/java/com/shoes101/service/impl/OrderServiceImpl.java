package com.shoes101.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shoes101.mapper.OrderdetailMapper;
import com.shoes101.mapper.ShoesorderMapper;
import com.shoes101.mapper.UseraddressMapper;
import com.shoes101.pojo.Orderdetail;
import com.shoes101.pojo.Shoesdailysales;
import com.shoes101.pojo.Shoesorder;
import com.shoes101.pojo.Useraddress;
import com.shoes101.service.OrderService;
import com.shoes101.vo.OrderVo;
import com.shoes101.vo.ShoesorderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderdetailMapper orderdetailMapper;

    @Autowired
    ShoesorderMapper shoesorderMapper;

    @Autowired
    UseraddressMapper useraddressMapper;

    public String add(OrderVo orderItem)
    {
        Integer totalprice = 0;

        //计算总价
        for(int i = 0;i < orderItem.getSkuidandqty().size();i++)
        {
            //根据skuid获取价格
            totalprice = totalprice + shoesorderMapper.getPriceFromSku(orderItem.getSkuidandqty().get(i).getSkuid()) *
                                        orderItem.getSkuidandqty().get(i).getQuantity();
            int sku = shoesorderMapper.getQuantityFromSku(orderItem.getSkuidandqty().get(i).getSkuid());
            sku = sku - orderItem.getSkuidandqty().get(i).getQuantity();
            //减库存 获取到库存直接更换 不减了
            //10.21 Sku有销量统计 可用作查询Top10
            shoesorderMapper.setNewSales(sku,orderItem.getSkuidandqty().get(i).getSkuid());
            shoesorderMapper.setNewSku(orderItem.getSkuidandqty().get(i).getSkuid(),sku);
        }

        System.out.println(totalprice);
        Date currentTime = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //10.21 将Shoesorder改为 ShoesorderVo 避免隐藏的日期格式冲突
        ShoesorderVo shoesorder = new ShoesorderVo();
        shoesorder.setUserid(orderItem.getUserid());
        //隐藏日期格式冲突
        shoesorder.setBuydate(df.format(currentTime));

        shoesorder.setCancel(0);
        shoesorder.setContactname(orderItem.getContactName());
        shoesorder.setContactphone(orderItem.getContactPhone());
        shoesorder.setReceiptaddress(orderItem.getReceiptaddress());
        shoesorder.setRemark(orderItem.getRemark());
        shoesorder.setState(1);
        shoesorder.setValidity(1);
        shoesorder.setTotalprice(totalprice.doubleValue());
        shoesorder.setTotticketprice(totalprice.doubleValue());

        //存进去
        shoesorderMapper.insert(shoesorder);
        //根据list的长度来一条条存入数据库
        //10.21更新 下单时直接有销量
        //Sku表有销量统计
        for(int i = 0;i < orderItem.getSkuidandqty().size();i++)
        {
            Orderdetail orderdetail = new Orderdetail();
            orderdetail.setOrderid(orderdetailMapper.getLateOrderId());
            orderdetail.setShoesname(orderdetailMapper.getNameById(orderdetailMapper.getIdBySkuid(orderItem.getSkuidandqty().get(i).getSkuid())));
            orderdetail.setSkuid(orderItem.getSkuidandqty().get(i).getSkuid());
            orderdetail.setQuantity(orderItem.getSkuidandqty().get(i).getQuantity());
            orderdetail.setPrice(totalprice.doubleValue());
            orderdetail.setTicketprice(totalprice.doubleValue());
            orderdetailMapper.insert(orderdetail);
        }
        return "success";
    }

    public String check(int userid)
    {
        List<Shoesorder> sorder = shoesorderMapper.getOrderByUserId(userid);
        Map<String,Object> map = new HashMap<>();
        map.put("userorder",sorder);
        return JSONObject.toJSONString(map);
    }

    public String checkOrderDetail(int orderid)
    {
        List<Orderdetail> odlist = orderdetailMapper.getOrderdetail(orderid);
        Map<String,Object> map = new HashMap<>();
        map.put("odetail",odlist);
        return JSONObject.toJSONString(map);
    }

    public String getAddress(int shoesid)
    {
        List<Useraddress> list = useraddressMapper.getUA(shoesid);
        Map<String,Object> map = new HashMap<>();
        map.put("useraddress",list);
        return JSONObject.toJSONString(map);
    }

}
