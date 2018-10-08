package com.shoes101.controller.FrontStage;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.redis.FGoodsKey;
import com.shoes101.redis.RedisService;
import com.shoes101.result.Result;
import com.shoes101.service.GoodsFService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 前台商品展示
 */
@Controller
@RequestMapping("/goodsf")
public class GoodsFController {

    @Autowired
    private GoodsFService goodsFService;

    @Autowired
    private RedisService redisService;

    /**
     * 获取商品列表
     * 根据商品id获取商品图片、价格、名称、并且获取所有库存不为0
     */
    @RequestMapping("/tolist")
    @ResponseBody
    public Result<String> tolist()
    {
//        System.out.println("11");
        //取缓存
        String existList = redisService.get(FGoodsKey.getGoodsList,"good_list",String.class);
//        System.out.println("111");
//        System.out.println(existList);
//        System.out.println("111");
        if(existList != null)
        {
            System.out.println("Using Redis to get Goodslist");
            return JSONObject.toJavaObject(JSONObject.parseObject(existList),Result.class);
        }
        //若缓存不存在
        Result result=Result.success(goodsFService.tolist());
        redisService.set(FGoodsKey.getGoodsList,"good_list",JSONObject.toJSONString(result));
        return result;
    }


    /**
     * 获取商品详情 传入商品Id
     *
     */
    @RequestMapping("/todetail")
    @ResponseBody
    public Result<String> todetail(@RequestParam("shoesid") int shoesid)
    {
        //取缓存
        String existDetail = redisService.get(FGoodsKey.getGoodsDetail,""+shoesid,String.class);
        if(existDetail != null)
        {
            System.out.println("Using Redis to get detail");
            return JSONObject.toJavaObject(JSONObject.parseObject(existDetail),Result.class);
        }
        //若缓存不存在
        Result result=Result.success(goodsFService.todetail(shoesid));
        redisService.set(FGoodsKey.getGoodsDetail,"good_detail",JSONObject.toJSONString(result));
        return result;
    }


}
