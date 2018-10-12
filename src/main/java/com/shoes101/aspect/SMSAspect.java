package com.shoes101.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shoes101.access.SmsLimit;
import com.shoes101.exception.GlobalException;
import com.shoes101.redis.RedisService;
import com.shoes101.redis.SmsKey;
import com.shoes101.result.CodeMsg;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class SMSAspect {
    private final static Logger logger = LoggerFactory.getLogger(SMSAspect.class);

    @Autowired
    RedisService redisService;

    @Before("execution(public * com.shoes101.service.impl.VerifyUserServiceImpl.loginSMSCode(..))&& @annotation(smsLimit)")
    public void SMSLimit(JoinPoint joinpoint, SmsLimit smsLimit) {
        int seconds = smsLimit.seconds();
        int maxCount = smsLimit.maxCount();
        String method=smsLimit.method();
        JSONObject jsonObject =getParams(joinpoint);
        String mobile=(String) jsonObject.get("mobile");
        String key =mobile;
        logger.info("mobile={}", mobile);
        SmsKey smsKey = SmsKey.smsLimitKey(seconds,method);
        Integer count = redisService.get(smsKey, key, Integer.class);
        logger.info(JSONObject.toJSONString(smsLimit));
        logger.info(key+":"+count);
        if(count  == null) {
            redisService.set(smsKey, key, 1);
        }else if(count < maxCount) {
            redisService.incr(smsKey, key);
        }else {
            throw new GlobalException(CodeMsg.SMS_FREQUENTLY_CODE);
        }

   }

    @Before("execution(public * com.shoes101.service.impl.VerifyUserServiceImpl.login(..))&& @annotation(smsLimit)")
    public void login(JoinPoint joinpoint, SmsLimit smsLimit) {
        int seconds = smsLimit.seconds();
        int maxCount = smsLimit.maxCount();
        String method=smsLimit.method();
        JSONObject jsonObject =getParams(joinpoint);
        String mobile=(String) jsonObject.get("mobile");
        String key =mobile;
        logger.info("mobile={}", mobile);
        SmsKey smsKey = SmsKey.smsLimitKey(seconds,method);
        Integer count = redisService.get(smsKey, key, Integer.class);
        logger.info(JSONObject.toJSONString(smsLimit));
        logger.info(key+":"+count);
        if(count  == null) {
            redisService.set(smsKey, key, 1);
        }else if(count < maxCount) {
            redisService.incr(smsKey, key);
        }else {
            throw new GlobalException(CodeMsg.USER_FREQUENTLY_LOGIN);
        }

    }

    @Before("execution(public * com.shoes101.service.impl.VerifyUserServiceImpl.registerSMSCode(..))&& @annotation(smsLimit)")
    public void registerCode(JoinPoint joinpoint, SmsLimit smsLimit) {
        int seconds = smsLimit.seconds();
        int maxCount = smsLimit.maxCount();
        String method=smsLimit.method();
        JSONObject jsonObject =getParams(joinpoint);
        String mobile=(String) jsonObject.get("mobile");
        String key =mobile;
        logger.info("mobile={}", mobile);
        SmsKey smsKey = SmsKey.smsLimitKey(seconds,method);
        Integer count = redisService.get(smsKey, key, Integer.class);
        logger.info(key+":"+count);
        if(count  == null) {
            redisService.set(smsKey, key, 1);
        }else if(count < maxCount) {
            redisService.incr(smsKey, key);
        }else {
            throw new GlobalException(CodeMsg.USER_FREQUENTLY_REGISTERCODE);
        }

    }

    @Before("execution(public * com.shoes101.service.impl.VerifyUserServiceImpl.register(..))&& @annotation(smsLimit)")
    public void register(JoinPoint joinpoint, SmsLimit smsLimit) {
        int seconds = smsLimit.seconds();
        int maxCount = smsLimit.maxCount();
        String method=smsLimit.method();
        JSONObject jsonObject =getParams(joinpoint);
        String mobile=(String) jsonObject.get("phone");
        String key =mobile;
        logger.info("mobile={}", mobile);
        SmsKey smsKey = SmsKey.smsLimitKey(seconds,method);
        Integer count = redisService.get(smsKey, key, Integer.class);
        logger.info(key+":"+count);
        if(count  == null) {
            redisService.set(smsKey, key, 1);
        }else if(count < maxCount) {
            redisService.incr(smsKey, key);
        }else {
            throw new GlobalException(CodeMsg.USER_FREQUENTLY_REGSTER);
        }

    }

    @Before("execution(public * com.shoes101.service.impl.VerifyUserServiceImpl.restPasswordSMSCode(..))&& @annotation(smsLimit)")
    public void restPasswordSMSCode(JoinPoint joinpoint, SmsLimit smsLimit) {
        int seconds = smsLimit.seconds();
        int maxCount = smsLimit.maxCount();
        String method=smsLimit.method();
        JSONObject jsonObject =getParams(joinpoint);
        String mobile=(String) jsonObject.get("mobile");
        String key =mobile;
        logger.info("restPasswordSMSCode mobile={}", mobile);
        SmsKey smsKey = SmsKey.smsLimitKey(seconds,method);
        Integer count = redisService.get(smsKey, key, Integer.class);
        logger.info(key+":"+count);
        if(count  == null) {
            redisService.set(smsKey, key, 1);
        }else if(count < maxCount) {
            redisService.incr(smsKey, key);
        }else {
            throw new GlobalException(CodeMsg.USER_FREQUENTLY_PASSWORD);
        }

    }




   /* * 获取方法参数值并组装为JSONObject
     * @param joinPoint
     * @return
             */
   public static JSONObject getParams(JoinPoint joinPoint) {
       //获取参数值
       Object[] args = joinPoint.getArgs();
       if (args == null) {
           return null;
       }
       JSONObject params = new JSONObject();
       //对象接收参数
       try {
           String data = JSON.toJSONString(joinPoint.getArgs()[1]);
           params = JSON.parseObject(data);
       }
       //普通参数传入
       catch (Exception e){
           //获取参数名
           Signature signature = joinPoint.getSignature();
           MethodSignature methodSignature = (MethodSignature) signature;
           for(int i=0;i<methodSignature.getParameterNames().length;i++){
               params.put(methodSignature.getParameterNames()[i],args[i]);
           }
       }
       return params;
   }

}
