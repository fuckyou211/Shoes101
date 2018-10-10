package com.shoes101.controller.FrontStage;


import com.shoes101.result.Result;
import com.shoes101.service.VerifyUserService;
import com.shoes101.vo.LoginCodeVo;
import com.shoes101.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private  final Logger logger= LoggerFactory.getLogger(LoginController.class) ;
	
	@Autowired
    VerifyUserService verifyUserService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "front/login";
    }
    
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
    	logger.info(loginVo.toString());
    	//登录
    	String token = verifyUserService.login(response, loginVo);
    	return Result.success(token);
    }

    @RequestMapping("/do_loginCode")
    @ResponseBody
    public Result<String> doLoginCode(HttpServletResponse response, @Valid LoginCodeVo LoginCodeVo) {
        logger.info(LoginCodeVo.toString());
        //登录
        String token = verifyUserService.loginCode(response, LoginCodeVo);
        return Result.success(token);
    }

    @RequestMapping("/loginSMSCode")
    @ResponseBody
    public Result<String> loginSMSCode(HttpServletResponse response, @RequestParam("mobile") String mobile) {
        //获取登录验证码
        String code = verifyUserService.loginSMSCode(response,mobile);
        return Result.success(code);
    }




}
