package com.shoes101.controller.FrontStage;
import com.alibaba.fastjson.JSON;
import com.shoes101.controller.BackStage.GoodsMController;
import com.shoes101.redis.PrepareRushKey;
import com.shoes101.redis.RedisService;
import com.shoes101.service.PrepareRushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 秒杀控制器 准备工作
 */
@RequestMapping("/rush")
@Controller
public class RushBuyReadyController {

    private  final Logger logger= LoggerFactory.getLogger(RushBuyReadyController.class) ;

    @Autowired
    private PrepareRushService prepareRushService;

    @Autowired
    private RedisService redisService;



    @RequestMapping("/aa")
    public String aa()
    {
        return "front/shoes-detail";
    }

    @RequestMapping("/getAllRush")
//    @ResponseBody
    public String getAllRush(Map<String, Object> map)
    {
        //取缓存
        String existList = redisService.get(PrepareRushKey.showRushBuy,"rush_list",String.class);
        if(existList != null)
        {
            map.put("rushbuy",existList);
            System.out.println("Using Redis to get Rushlist");
            return "front/shoes-sale";
        }
        //若缓存不存在
        String rushlist = prepareRushService.getAllRush();
        redisService.set(PrepareRushKey.showRushBuy,"rush_list",rushlist);
        map.put("rushbuy",rushlist);
        return "front/shoes-sale";
//        return prepareRushService.getAllRush();
    }

	//@RequestMapping("/front/shoes-detail")
//	public String toDetail(){
//    	return "/front/shoes-detail";
//	}
//

    @RequestMapping("/selectOneRush")
    public String getOneRush(Map<String, Object> map, @RequestParam("rushbuyid") int rushbuyid)
    {

        logger.info("请求你啦！-----{}",rushbuyid);

//        String str = prepareRushService.getOneRush(rushbuyid);
//        System.out.println(str);
//        System.out.println(JSON.parse(str));
        map.put("detail",prepareRushService.getOneRush(rushbuyid));
//        System.out.println("asdf" + map.get("a"));
        return "/front/shoes-detail";
    }
}
