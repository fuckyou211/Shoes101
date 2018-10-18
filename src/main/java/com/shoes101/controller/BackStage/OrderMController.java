package com.shoes101.controller.BackStage;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.result.Result;
import com.shoes101.service.OrderService;
import com.shoes101.service.ShoesOrderService;
import com.shoes101.vo.ShoesorderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 后台订单控制器
 */
@RequestMapping("/orderm")
@Controller
public class OrderMController {
    @Autowired
    ShoesOrderService shoesOrderService;

    @Autowired
    OrderService orderService;

    //跳转订单
    @RequestMapping("/toorder")
    public String toorder(Map<String, Object> map){
        map.put("olist", JSONObject.toJSONString(shoesOrderService.getAllOrder()));
        return "back/manager_orders";
    }

    //根据状态点击按钮返回状态列表
    @RequestMapping("/getbyitemAjax")
    @ResponseBody
    public List<ShoesorderVo> getbyitem(@RequestParam("validity")int validity, @RequestParam("cancel")int cancel,
                                        @RequestParam("status")int status)
    {
        return shoesOrderService.getbyitem(validity,cancel,status);
    }

    //根据状态点击按钮返回状态列表
    @RequestMapping("/getAllAjax")
    @ResponseBody
    public List<ShoesorderVo> getAll()
    {
        return shoesOrderService.getAllOrder();
    }

    //发货或者退款
    @RequestMapping("/sendOrBackAjax")
    @ResponseBody
    public List<ShoesorderVo> sendOrBack(@RequestParam("orderid")int orderid,@RequestParam("validity")int validity,
                                         @RequestParam("cancel")int cancel,@RequestParam("state")int state)
    {
        System.out.println(orderid);
        System.out.println(validity);
        System.out.println(cancel);
        System.out.println(state);
        return shoesOrderService.sendOrBack(orderid,validity,cancel,state);
    }

    //转换日期
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,true));

    }
}
