package com.shoes101.controller.FrontStage;


import com.alibaba.fastjson.JSON;
import com.shoes101.pojo.Shopcartdetails;
import com.shoes101.result.Result;
import com.shoes101.service.ShopCartService;
import com.shoes101.vo.ShopCartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cart")
public class ShopCartController {

    @Autowired
    private ShopCartService cartService;

    @RequestMapping("/add")
    @ResponseBody
    public Result<String> addToCart(ShopCartVo shopCartVo){
        System.out.println("shopcart");
        String list = JSON.toJSONString(cartService.addShopCart(shopCartVo));
        return Result.success(list);
    }

    @RequestMapping("/remove")
    @ResponseBody
    public Result<String> removeCart(){

        return Result.success("");
    }

    @RequestMapping("/getShopCart")
    @ResponseBody
    public Result<String> getShopCart(){
        String list = JSON.toJSONString(cartService.getUserShopCart());
        return Result.success(list);
    }
}
