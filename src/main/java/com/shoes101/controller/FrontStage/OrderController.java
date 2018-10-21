package com.shoes101.controller.FrontStage;

import com.alibaba.fastjson.JSON;
import com.shoes101.access.UserContext;
import com.shoes101.pojo.User;
import com.shoes101.redis.PageDataKey;
import com.shoes101.redis.RedisService;
import com.shoes101.redis.UserKey;
import com.shoes101.result.Result;
import com.shoes101.service.OrderService;
import com.shoes101.vo.OrderVo;
import com.shoes101.vo.PageDataVo;
import com.shoes101.vo.SkuIdAndQuantityVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private  final Logger logger= LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private RedisService redisService;


    // 页面数据
    @RequestMapping("/pageData")
    @ResponseBody
    public Result<Integer> setOrderPageData(String key, String data){
        logger.info(" 保存订单页面的数据：key = {}, value = {}", key, data);

        redisService.set(PageDataKey.orderData, key, data);

        return Result.success(1);

    }

    @RequestMapping("/getOrderPageData")
    @ResponseBody
    public Result<String> getOrderPageData(String key){
        logger.info(" 获取页面的数据：key = {}", key);

        String list = redisService.get(PageDataKey.orderData,key,String.class);

        return Result.success(list);

    }


    //下单
    @RequestMapping("/add")
    public Result<String> setOrder(@RequestParam("orderItem") OrderVo orderItem)
    {


        return Result.success(orderService.add(orderItem));
    }

	@RequestMapping("/add-test")
	@ResponseBody
	public Result<String> setOrderTest(String skuidandqty,String contactPhone,
									   String contactName, String remark, String receiptaddress,String token){

    	logger.info("接收参数：{}，{}， {}， {}，{}",skuidandqty,contactPhone,contactName,remark,receiptaddress,token);

		List<SkuIdAndQuantityVo> skuiobj = JSON.parseArray(skuidandqty,SkuIdAndQuantityVo.class);

		User user = UserContext.getUser();
		if(user == null){
			user = redisService.get(UserKey.token,token,User.class);
		}
		if(user == null){
			user = new User();
			user.setUserid(123343);
		}
		OrderVo orderItem = new OrderVo();
		orderItem.setSkuidandqty(skuiobj);
		if(contactName == null || contactName.length() == 0){
			contactName ="xx";
		}
		orderItem.setContactName(contactName);
		if(contactPhone == null || contactPhone.length() == 0){
			contactPhone = "xxx";
		}
		orderItem.setContactPhone(contactPhone);
		if(receiptaddress == null || receiptaddress.length() == 0){
			receiptaddress = "xxx";
		}
		orderItem.setReceiptaddress(receiptaddress);
		if(remark == null || remark.length() == 0){
			remark = "xx";
		}
		orderItem.setRemark(remark);
		orderItem.setUserid(user.getUserid());

		return Result.success(orderService.add(orderItem));
	}



//	url: "/order/add-test",
//	type:"POST",
//	data: {
//		"skuidandqty": JSON.stringify(orderItemArr),
//				"contactPhone":contactPhone,
//				"contactName":contactName,
//				"remark":remark,
//				"receiptaddress":addr,
//				"token":token
//	},



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
