package com.shoes101.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.shoes101.access.SmsLimit;
import com.shoes101.exception.GlobalException;
import com.shoes101.mapper.UserMapper;
import com.shoes101.pojo.User;
import com.shoes101.redis.RedisService;
import com.shoes101.redis.UserKey;
import com.shoes101.result.CodeMsg;
import com.shoes101.service.VerifyUserService;
import com.shoes101.util.MD5Util;
import com.shoes101.util.SMSMethodUtils;
import com.shoes101.util.UUIDUtils;
import com.shoes101.vo.LoginCodeVo;
import com.shoes101.vo.LoginVo;
import com.shoes101.vo.ResetPasswordVo;
import com.shoes101.vo.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class VerifyUserServiceImpl implements VerifyUserService {

    public static final String COOKI_NAME_TOKEN = "token";
    private  final Logger logger= LoggerFactory.getLogger(VerifyUserService.class) ;

    @Autowired
    RedisService redisService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SMSMethodUtils sMSMethodUtils;

    public User getByMobile(String phone) {
        //取缓存
        User user = redisService.get(UserKey.getByPhone, ""+phone, User.class);
        if(user != null) {
            return user;
        }
        //取数据库
        user = userMapper.getByMobile(phone);

        if(user != null) {
            redisService.set(UserKey.getByPhone, ""+phone, user);
        }
        return user;
    }
   /* // http://blog.csdn.net/tTU1EvLDeLFq5btqiK/article/details/78693323
    public boolean updatePassword(String token, Integer id, String formPass) {
        //取user
        MiaoshaUser user = getById(id);
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //更新数据库
        MiaoshaUser toBeUpdate = new MiaoshaUser();
        toBeUpdate.setId(id);
        toBeUpdate.setPassword(MD5Util.formPassToDBPass(formPass, user.getSalt()));
        miaoshaUserDao.update(toBeUpdate);
        //处理缓存
        redisService.delete(MiaoshaUserKey.getById, ""+id);
        user.setPassword(toBeUpdate.getPassword());
        redisService.set(MiaoshaUserKey.token, token, user);
        return true;
    }*/


   @Override
    public User getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        User user = redisService.get(UserKey.token, token, User.class);
        //延长有效期
        if(user != null) {
            addCookie(response, token, user);
        }
        return user;
    }


    @Override
    @SmsLimit(seconds=60,maxCount=5,method = "login")
    public String login(HttpServletResponse response, LoginVo loginVo) {
        if(loginVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        User user =getByMobile(mobile);
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        else if(user.getCold()==1)
        {
            throw new GlobalException(CodeMsg.USER_COLD_ERROR);
        }
        //验证密码
        String dbPass = user.getPassword();
        String calcPass = MD5Util.formPassToDBPass(formPass, MD5Util.getSalt());
        logger.info("calcPass:"+calcPass);
        if(!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token= UUIDUtils.getUUID();
        addCookie(response, token, user);
        return token;
    }

    private void addCookie(HttpServletResponse response, String token, User user) {
        redisService.set(UserKey.token, token, user);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @Override
    @SmsLimit(seconds=60,maxCount=5,method = "loginCode")
    public String loginCode(HttpServletResponse response, LoginCodeVo loginCodeVo) {
        if(loginCodeVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = loginCodeVo.getMobile();
        String code = loginCodeVo.getCode();
        //判断手机号是否存在
        User user =getByMobile(mobile);
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        else if(user.getCold()==1)
        {
            throw new GlobalException(CodeMsg.USER_COLD_ERROR);
        }
        //验证code
        String rdcode = redisService.get(UserKey.loginCode,mobile,String.class);
        logger.info("rdcode:"+rdcode);
        if(rdcode==null)
        {
            throw new GlobalException(CodeMsg.USER_NOT_GETCODE);
        }
        if(!rdcode.equals(code)) {
            throw new GlobalException(CodeMsg.USER_CODE_ERROR);
        }
        //生成cookie
        String token= UUIDUtils.getUUID();
        addCookie(response, token, user);
        return token;
    }

    @Override
    @SmsLimit(seconds=300,maxCount=5,method = "loginSMSCode")
    public String loginSMSCode(HttpServletResponse response, String mobile) {
        User user =getByMobile(mobile);
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        else if(user.getCold()==1)
        {
            throw new GlobalException(CodeMsg.USER_COLD_ERROR);
        }
        String code=getCode();
        redisService.set(UserKey.loginCode,mobile,code);
        logger.info(JSONObject.toJSONString(redisService.get(UserKey.loginCode,mobile,String.class)));

//        try {
//            sMSMethodUtils.loginCode(mobile,code);
//        } catch (ClientException e) {
//            e.printStackTrace();
//            throw new GlobalException(CodeMsg.SMS_VERIFICATION_CODE);
//        }
        return code;
    }

    @Override
    @SmsLimit(seconds=300,maxCount=5,method = "registerSMSCode")
    public String registerSMSCode(HttpServletResponse response, String mobile) {
        User user =getByMobile(mobile);
        if(user != null) {
            throw new GlobalException(CodeMsg.MOBILE_EXITS_REGISTER);
        }

        String code=getCode();
        redisService.set(UserKey.registerCode,mobile,code);
        logger.info(JSONObject.toJSONString(redisService.get(UserKey.registerCode,mobile,String.class)));

       /* try {
            sMSMethodUtils.registerCode(mobile,code);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GlobalException(CodeMsg.SMS_VERIFICATION_CODE);
        }*/
        return code;
    }

    @Override
    @SmsLimit(seconds=60,maxCount=5,method = "register")
    public String register(HttpServletResponse response, UserVo userVo, String code) {
        if(userVo == null) {
            throw new GlobalException(CodeMsg.REQUEST_ILLEGAL);
        }
        String mobile = userVo.getPhone();
        //判断手机号是否存在
        User RDuser =getByMobile(mobile);
        if(RDuser != null) {
            throw new GlobalException(CodeMsg.MOBILE_EXITS_REGISTER);
        }

        //验证code
        String rdcode = redisService.get(UserKey.registerCode,mobile,String.class);
        logger.info("rdcode:"+rdcode);
        if(rdcode==null)
        {
            throw new GlobalException(CodeMsg.USER_NOT_GETCODE);
        }
        if(!rdcode.equals(code)) {
            throw new GlobalException(CodeMsg.USER_CODE_ERROR);
        }
        User user=new User();
        user.setCold(0);
        user.setUsername(userVo.getUsername());
        String password=MD5Util.formPassToDBPass(userVo.getPassword(), MD5Util.getSalt());
        user.setPassword(password);
        user.setGender(userVo.getGender());
        user.setPhone(userVo.getPhone());
        logger.info("user:"+JSONObject.toJSONString(user));
        userMapper.insert(user);
        return "注册成功！";
    }

    @Override
    @SmsLimit(seconds=300,maxCount=5,method = "restPasswordSMSCode")
    public String restPasswordSMSCode(HttpServletResponse response, String mobile) {
        User user =getByMobile(mobile);
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        else if(user.getCold()==1)
        {
            throw new GlobalException(CodeMsg.USER_COLD_ERROR);
        }
        String code=getCode();
        redisService.set(UserKey.resetPasswordCode,mobile,code);
        logger.info(JSONObject.toJSONString(redisService.get(UserKey.resetPasswordCode,mobile,String.class)));

//        try {
//            sMSMethodUtils.resetPasswordCode(mobile,code);
//        } catch (ClientException e) {
//            e.printStackTrace();
//            throw new GlobalException(CodeMsg.SMS_VERIFICATION_CODE);
//        }
        return code;

    }

    @Override
    public String restPassword(HttpServletResponse response, ResetPasswordVo resetPasswordVo) {
        if(resetPasswordVo == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String mobile = resetPasswordVo.getMobile();
        String code = resetPasswordVo.getCode();
        //判断手机号是否存在
        User user =getByMobile(mobile);
        if(user == null) {
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        else if(user.getCold()==1)
        {
            throw new GlobalException(CodeMsg.USER_COLD_ERROR);
        }
        //验证code
        String rdcode = redisService.get(UserKey.resetPasswordCode,mobile,String.class);
        logger.info("rdcode:"+rdcode);
        if(rdcode==null)
        {
            throw new GlobalException(CodeMsg.USER_RESETPASSWORDCODE_ERROR);
        }
        if(!rdcode.equals(code)) {
            throw new GlobalException(CodeMsg.USER_RESETPASSWORDCODE_ERROR);
        }
        user.setPassword(MD5Util.formPassToDBPass(resetPasswordVo.getPassword(), MD5Util.getSalt()));
        logger.info("user:"+JSONObject.toJSONString(user));
        userMapper.updateByPrimaryKey(user);
        return "密码修改成功!";
    }

    public static String getCode() {
        String SYMBOLS = "0123456789";
        Random RANDOM = new SecureRandom();
        // 如果需要4位，那 new char[4] 即可，其他位数同理可得
        char[] nonceChars = new char[6];
        for (int index = 0; index < nonceChars.length; ++index) {
            nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
        }
        return new String(nonceChars);
    }
}
