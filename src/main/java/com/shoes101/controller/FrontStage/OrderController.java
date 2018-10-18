package com.shoes101.controller.FrontStage;

import com.shoes101.redis.RedisService;
import com.shoes101.result.Result;
import com.shoes101.service.OrderService;
import com.shoes101.vo.OrderVo;
import com.shoes101.vo.PageDataVo;
import com.shoes101.vo.SkuIdAndQuantityVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisService redisService;


    // 页面数据
    @PostMapping("/pageData")
    public Result<PageDataVo> setOrderPageData(@RequestParam(value="key") String key, @RequestParam(value="data") String data){
        return null;

    }


    //下单
    @RequestMapping("/add")
    public Result<String> setOrder(@RequestParam("orderItem") OrderVo orderItem)
    {


        return Result.success(orderService.add(orderItem));
    }

    //查询所有订单 传入userid 返回JSONString
    @RequestMapping("/check")
    @ResponseBody
    public Result<String> checkOrder(@RequestParam("userid") int userid)
    {
        return  Result.success((orderService.check(userid)));
    }

    //查询订单详情 传入orderid 返回JSONString
    @RequestMapping("/checkDetail")
    @ResponseBody
    public Result<String> checkOrderDetail(@RequestParam("orderid") int orderid)
    {
        return  Result.success((orderService.checkOrderDetail(orderid)));
    }

    //获取用户的收货地址
    @RequestMapping("/getAddress")
    @ResponseBody
    public Result<String> getAddress(@RequestParam("userid") int userid)
    {
        return Result.success(orderService.getAddress(userid));
    }

    //申请退货 状态修改未知

}
