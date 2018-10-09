package com.shoes101.access;


import com.shoes101.pojo.Admin;
import com.shoes101.redis.RedisService;
import com.shoes101.result.CodeMsg;
import com.shoes101.service.AdminService;
import com.shoes101.service.impl.AdminServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class AdminInterceptor extends HandlerInterceptorAdapter {

//	private  final Logger logger= LoggerFactory.getLogger(AdminInterceptor.class) ;
//	@Autowired
//	AdminService adminService;
//
//	@Autowired
//	RedisService redisService;
//
//	@Override
//	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
//			throws Exception {
//		if(handler instanceof HandlerMethod) {
//			Admin admin = getAdmin(request, response);
//			AdminContext.setAdmin(admin);
//			HandlerMethod hm = (HandlerMethod)handler;
//			logger.info("拦截类 路径 getPackage ："+hm.getMethod().getDeclaringClass().getPackage().getName());
//			if(!hm.getMethod().getDeclaringClass().getPackage().getName().equals("com.shoes101.controller.BackStage"))
//			{
//				logger.info("AdminLimit 未拦截！");
//				return true;
//			}
//			if((!hm.getMethod().getName().equals("login")&&!hm.getMethod().getName().equals("doLogin"))&&admin==null)
//			{
//				logger.info("用户未登录，拦截 hm.getMethod():"+hm.getMethod().getName());
//				AccessInterceptor.render(response, CodeMsg.ADMIN_NOT_LOGIN);
//				return false;
//			}
//			AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
//			if(accessLimit == null) {
//				logger.info("AdminLimit 未拦截！");
//				return true;
//			}
//		}
//		return true;
//	}
//
//
//	private Admin getAdmin(HttpServletRequest request, HttpServletResponse response) {
//		String paramToken = request.getParameter(AdminServiceImpl.COOKI_NAME_TOKEN);
//		String cookieToken = getCookieValue(request, AdminServiceImpl.COOKI_NAME_TOKEN);
//		if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
//			return null;
//		}
//		String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
//		return adminService.getByToken(response,token);
//	}
//
//	private String getCookieValue(HttpServletRequest request, String cookiName) {
//		Cookie[]  cookies = request.getCookies();
//		if(cookies == null || cookies.length <= 0){
//			return null;
//		}
//		for(Cookie cookie : cookies) {
//			if(cookie.getName().equals(cookiName)) {
//				return cookie.getValue();
//			}
//		}
//		return null;
//	}
	
}
