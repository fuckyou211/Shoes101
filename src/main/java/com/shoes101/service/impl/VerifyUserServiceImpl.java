package com.shoes101.service.impl;

import com.shoes101.exception.GlobalException;
import com.shoes101.mapper.UserMapper;
import com.shoes101.pojo.User;
import com.shoes101.redis.RedisService;
import com.shoes101.redis.UserKey;
import com.shoes101.result.CodeMsg;
import com.shoes101.service.VerifyUserService;
import com.shoes101.util.MD5Util;
import com.shoes101.util.UUIDUtils;
import com.shoes101.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class VerifyUserServiceImpl implements VerifyUserService {

    public static final String COOKI_NAME_TOKEN = "token";
    private  final Logger logger= LoggerFactory.getLogger(VerifyUserService.class) ;

    @Autowired
    RedisService redisService;

    @Autowired
    private UserMapper userMapper;

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



}
