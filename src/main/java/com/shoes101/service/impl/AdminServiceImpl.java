package com.shoes101.service.impl;


import com.alibaba.druid.util.StringUtils;
import com.shoes101.exception.GlobalException;
import com.shoes101.mapper.back.AdminMapper;
import com.shoes101.pojo.Admin;
import com.shoes101.redis.AdminKey;
import com.shoes101.redis.RedisService;
import com.shoes101.result.CodeMsg;
import com.shoes101.service.AdminService;
import com.shoes101.util.MD5Util;
import com.shoes101.util.UUIDUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class AdminServiceImpl implements AdminService {

    private  final Logger logger= LoggerFactory.getLogger(AdminServiceImpl.class) ;


    public static final String COOKI_NAME_TOKEN = "tokenAdmin";
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    RedisService redisService;

    public Admin getByUserName(String userName) {
        //取缓存
        Admin admin = redisService.get(AdminKey.getByUserName, ""+userName, Admin.class);
        if(admin != null) {
            return admin;
        }
        //取数据库
        admin = adminMapper.findAdmin(userName);

        if(admin != null) {
            redisService.set(AdminKey.getByUserName, ""+userName, admin);
        }
        return admin;
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
    public Admin getByToken(HttpServletResponse response, String token) {
        if(StringUtils.isEmpty(token)) {
            return null;
        }
        Admin admin = redisService.get(AdminKey.token, token, Admin.class);
        //延长有效期
        if(admin != null) {
            addCookie(response, token, admin);
        }
        return admin;
    }


    @Override
    public String login(HttpServletResponse response, Admin admin) {
        if(admin == null) {
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        }
        String userName = admin.getAdminname();
        String formPass = admin.getPassword();
        //判断手机号是否存在
        Admin admin1 =getByUserName(userName);
        if(admin1 == null) {
            throw new GlobalException(CodeMsg.ADMIN_NOT_EXIST);
        }
        //验证密码
        String dbPass = admin1.getPassword();
        String calcPass = MD5Util.formPassToDBPass(formPass, MD5Util.getSalt());
        logger.info("calcPass:"+calcPass);
        logger.info("formPass:"+formPass+"  dbPass:"+dbPass);
        if(!calcPass.equals(dbPass)) {
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        //生成cookie
        String token= UUIDUtils.getUUID();
        addCookie(response, token, admin1);
        return token;
    }

    private void addCookie(HttpServletResponse response, String token, Admin admin) {
        redisService.set(AdminKey.token, token, admin);
        Cookie cookie = new Cookie(COOKI_NAME_TOKEN, token);
        cookie.setMaxAge(AdminKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
