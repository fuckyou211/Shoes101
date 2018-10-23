package com.shoes101.controller.FrontStage;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.pojo.User;
import com.shoes101.rabbitmq.MQSender;
import com.shoes101.rabbitmq.MiaoshaMessage;
import com.shoes101.redis.RedisService;
import com.shoes101.redis.UserKey;
import com.shoes101.result.CodeMsg;
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
import javax.swing.*;
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

    /**
     *  修改一下，方便传值
     * @param request
     * @param user
     * @param rushOrderVo
     * @return
     */
    @RequestMapping("/CreateRushOrder2")
    @ResponseBody
    public Result<String> CreateRushOrder2(HttpServletRequest request, String skuidandqty, String contactPhone,
                                           String contactName, String remark, String receiptaddress, String token, String rushbuyid) {

        if(token == null || token.length() == 0){
            return Result.error(CodeMsg.USER_FREQUENTLY_LOGIN);
        }

        User user = redisService.get(UserKey.token,token,User.class);

        if(user == null){
            return Result.error(CodeMsg.USER_FREQUENTLY_LOGIN);
        }

        logger.info("user:"+ JSONObject.toJSONString(user));
        MiaoshaMessage miaoshaMessage=new MiaoshaMessage();
        miaoshaMessage.setUser(user);

        RushOrderVo rushOrderVo = new RushOrderVo();
        if(contactName == null || contactName.length() == 0){
            contactName ="xx";
        }
        rushOrderVo.setContactName(contactName);
        if(contactPhone == null || contactPhone.length() == 0){
            contactPhone = "xxx";
        }
        rushOrderVo.setContactPhone(contactPhone);
        if(receiptaddress == null || receiptaddress.length() == 0){
            receiptaddress = "xxx";
        }
        rushOrderVo.setReceiptaddress(receiptaddress);
        if(remark == null || remark.length() == 0){
            remark = "xx";
        }
        rushOrderVo.setRemark(remark);

        if(rushbuyid == null || rushbuyid.length() == 0){
            return Result.error(CodeMsg.MIAOSHA_FAIL);
        }
        rushOrderVo.setRushbuyid(Integer.parseInt(rushbuyid));
        rushOrderVo.setUserid(user.getUserid());

        miaoshaMessage.setRushOrderVo(rushOrderVo);

        return Result.success("信息修改成功！");
    }

}
