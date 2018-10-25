package com.shoes101.service.impl;


import com.shoes101.exception.GlobalException;
import com.shoes101.mapper.RushbuyMapper;
import com.shoes101.pojo.Rushsku;
import com.shoes101.pojo.User;
import com.shoes101.rabbitmq.MQSender;
import com.shoes101.rabbitmq.MiaoshaMessage;
import com.shoes101.redis.RedisService;
import com.shoes101.redis.RushKey;

import com.shoes101.result.CodeMsg;
import com.shoes101.result.Result;
import com.shoes101.service.RushOrderService;
import com.shoes101.vo.RushOrderVo;
import com.shoes101.vo.RushbuyVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RushOrderServiceImpl implements RushOrderService {
    private final Logger logger = LoggerFactory.getLogger(RushOrderServiceImpl.class);

    @Autowired
    RedisService redisService;

    @Autowired
    RushbuyMapper rushbuyMapper;


    @Autowired
    MQSender mqSender;


    @Override
    public Result<String> CreatRushOrder(HttpServletRequest request, User user, RushOrderVo rushOrderVo) {

        if (!redisService.exists(RushKey.rushsku, rushOrderVo.getRushbuyid() + ":" + rushOrderVo.getShoessku())) {
            redisSave(rushOrderVo);
        }
        Long state = redisService.get(RushKey.orderState, user.getUserid() + ":" + rushOrderVo.getRushbuyid(), Long.class);
        if (state == null) {
            state = Long.valueOf(-1);
        }
        if (state == 0) {
            return Result.error(CodeMsg.MIAOSHA_WAIT);
        } else if (state.equals(2)) {
            return Result.error(CodeMsg.MIAOSHA_ORDERNULL);
        }
        Long overnumber = redisService.get(RushKey.userLimit, user.getUserid() + ":" + rushOrderVo.getRushbuyid(), Long.class);
        Long limitnumber = redisService.get(RushKey.rushLimit, "" + rushOrderVo.getRushbuyid(), Long.class);
        if (overnumber == null) {
            overnumber = Long.valueOf(0);
        }
        if (limitnumber < overnumber + rushOrderVo.getQuantity()) {
            redisService.set(RushKey.orderState, user.getUserid() + ":" + rushOrderVo.getRushbuyid(), -1);
            return Result.error(new CodeMsg(5000,"商品限购"+limitnumber+"件,您购买的商品数量已超过规定额度！"));
        }

        Long number = redisService.decrBy(RushKey.rushsku, rushOrderVo.getRushbuyid() + ":" + rushOrderVo.getShoessku(), rushOrderVo.getQuantity());
        if (number < 0) {
            redisService.incrBy(RushKey.rushsku, rushOrderVo.getRushbuyid() + ":" + rushOrderVo.getShoessku(), rushOrderVo.getQuantity());
            redisService.set(RushKey.orderState, user.getUserid() + ":" + rushOrderVo.getRushbuyid(), -1);
            return Result.error(CodeMsg.MIAOSHA_NULLGOOD);
        }
        //入队
        redisService.set(RushKey.orderState, user.getUserid() + ":" + rushOrderVo.getRushbuyid(), 0);
        MiaoshaMessage mm = new MiaoshaMessage();
        mm.setUser(user);
        mm.setRushOrderVo(rushOrderVo);
        mqSender.sendMiaoshaMessage(mm);

        return Result.success("0");
    }

    public boolean redisSave(RushOrderVo rushOrderVo) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        RushbuyVo rushbuy = rushbuyMapper.getRushbuyByGoodid(rushOrderVo.getRushbuyid());

        try {
            int begintime = date.compareTo(dateFormat.parse(rushbuy.getBegintime()));
            int endtime = date.compareTo(dateFormat.parse(rushbuy.getEndtime()));

            if (begintime == 1 && endtime == -1) {
                List<Rushsku> rushskuList = rushbuyMapper.getRushbuyByGoodrushbuyid(rushbuy.getRushbuyid());
                Calendar c1 = Calendar.getInstance();
                Calendar c2 = Calendar.getInstance();
                c1.setTime(dateFormat.parse(rushbuy.getEndtime()));
                c2.setTime(date);
                int timeEnd = c1.get(Calendar.SECOND);
                int timeNew = c2.get(Calendar.SECOND);
                redisService.set(new RushKey(timeEnd - timeNew, "rushLimit"), "" + rushbuy.getRushbuyid(), rushbuy.getLimitN());
                logger.info("RushTime:{}", timeEnd - timeNew);
                logger.info("Rushbuy:{}", rushbuy);
                for (int i = 0; i < rushskuList.size(); i++) {
                    redisService.set(new RushKey(timeEnd - timeNew, "rushsku"), rushbuy.getRushbuyid() + ":" + rushskuList.get(i).getSkuid(), rushskuList.get(i).getQuantity());
                }
            } else if (begintime == -1) {
                throw new GlobalException(CodeMsg.MIAOSHA_NULLBEGIN);
            } else {
                throw new GlobalException(CodeMsg.MIAOSHA_END);
            }

        } catch (ParseException e) {
            throw new GlobalException(CodeMsg.MIAOSHA_END);
        }
        return false;

    }


    @Override
    public Result<String> QueryRushOrder(HttpServletRequest request, User user, RushOrderVo rushOrderVo) {
        Long state = redisService.get(RushKey.orderState, user.getUserid() + ":" + rushOrderVo.getRushbuyid(), Long.class);

        if (state == null || state == -1) {
            state = Long.valueOf(-1);
            return Result.error(CodeMsg.MIAOSHA_FAIL);
        } else if (state == 0) {
            return Result.error(CodeMsg.MIAOSHA_WAIT);
        } else if (state == 1) {
            Long orderid=redisService.get(RushKey.orderId,user.getUserid()+":"+rushOrderVo.getRushbuyid(),Long.class);
            return Result.success(""+orderid);
        }
        return Result.error(CodeMsg.MIAOSHA_FAIL);
    }

}