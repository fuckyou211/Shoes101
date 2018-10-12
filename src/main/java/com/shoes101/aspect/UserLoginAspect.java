package com.shoes101.aspect;

import com.shoes101.exception.GlobalException;
import com.shoes101.mapper.UserMapper;
import com.shoes101.pojo.User;
import com.shoes101.redis.RedisService;
import com.shoes101.result.CodeMsg;
import com.shoes101.service.VerifyUserService;
import com.shoes101.service.impl.VerifyUserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Order(1)
@Component
@Aspect
public class UserLoginAspect {
    private final static Logger logger = LoggerFactory.getLogger(UserLoginAspect.class);

    @Autowired
    VerifyUserService verifyUserServiceImpl;

    @Autowired
    RedisService redisService;

    @Autowired
    private UserMapper userMapper;

    //@Before("execution(public * com.shoes101.controller.FrontStage.*.*(..))")
    public void UserLogin(JoinPoint joinpoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        logger.info("class_method={}", joinpoint.getSignature().getDeclaringTypeName() + "-" + joinpoint.getSignature().getName());
        if(joinpoint.getSignature().getDeclaringTypeName().equals("com.shoes101.controller.FrontStage.LoginController")) {
            return;
        }
        User user= getUser(request,response);
        logger.info("user={}",user);
        if(user==null)
        {
            throw new GlobalException(CodeMsg.ADMIN_NOT_LOGIN);
        }

    }

    private  User getUser(HttpServletRequest request, HttpServletResponse response) {
        String paramToken = request.getParameter(VerifyUserServiceImpl.COOKI_NAME_TOKEN);
        String cookieToken = getCookieValue(request, VerifyUserServiceImpl.COOKI_NAME_TOKEN);
        if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
            return null;
        }
        String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
        return verifyUserServiceImpl.getByToken(response, token);
    }

    private String getCookieValue(HttpServletRequest request, String cookiName) {
        Cookie[]  cookies = request.getCookies();
        if(cookies == null || cookies.length <= 0){
            return null;
        }
        for(Cookie cookie : cookies) {
            if(cookie.getName().equals(cookiName)) {
                return cookie.getValue();
            }
        }
        return null;
    }


}
