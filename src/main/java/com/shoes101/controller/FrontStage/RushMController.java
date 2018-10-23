package com.shoes101.controller.FrontStage;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.pojo.User;
import com.shoes101.rabbitmq.MQSender;
import com.shoes101.rabbitmq.MiaoshaMessage;
import com.shoes101.redis.RedisService;
import com.shoes101.result.Result;
import com.shoes101.vo.OrderVo;
import com.shoes101.vo.RushOrderVo;
import com.shoes101.vo.UserImformationVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/RushOrder")
public class RushMController {
    private  final Logger logger= LoggerFactory.getLogger(RushMController.class);
    @Autowired
    RedisService redisService;

    @RequestMapping("/CreateRushOrder")
    @ResponseBody
    public Result<String> CreateRushOrder(HttpServletRequest request, User user,RushOrderVo rushOrderVo)
    {
        logger.info("user:"+ JSONObject.toJSONString(user));
        MiaoshaMessage miaoshaMessage=new MiaoshaMessage();
        miaoshaMessage.setUser(user);
        miaoshaMessage.setRushOrderVo(rushOrderVo);

        return Result.success("信息修改成功！");
    }

}
