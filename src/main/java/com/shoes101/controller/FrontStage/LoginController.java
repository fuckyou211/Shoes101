package com.shoes101.controller.FrontStage;


import com.shoes101.pojo.User;
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

	//跳转登录页面
    @RequestMapping("/to_login")
    public String toLogin() {
        return "front/login";
    }


    /**
     * 账号密码登录接口
     * @param response
     * @param loginVo 封装类 传的数据命名 按bean中变量名字
     * @return
     */
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
    	logger.info(loginVo.toString());
    	//登录
    	String token = verifyUserService.login(response, loginVo);
    	return Result.success(token);
    }

    /**
     * 用户短信登录接口
     * @param response
     * @param loginCodeVo 封装类 传的数据命名 按bean中变量名字命名
     * @return
     */
    @RequestMapping("/do_loginCode")
    @ResponseBody
    public Result<String> doLoginCode(HttpServletResponse response, @Valid LoginCodeVo loginCodeVo) {
        logger.info(loginCodeVo.toString());
        //登录
        String token = verifyUserService.loginCode(response, loginCodeVo);
        return Result.success(token);
    }

    /**
     * 登录短信码接口
     * @param response
     * @param mobile  手机号码
     * @return
     */
    @RequestMapping("/loginSMSCode")
    @ResponseBody
    public Result<String> loginSMSCode(HttpServletResponse response, @RequestParam("mobile") String mobile) {
        //获取登录验证码
        String code = verifyUserService.loginSMSCode(response,mobile);
        return Result.success("验证码已发送，请查收！");
    }

    /**
     * 注册短信码接口
     * @param response
     * @param mobile  手机号码
     * @return
     */
    @RequestMapping("/registerSMSCode")
    @ResponseBody
    public Result<String> registerSMSCode(HttpServletResponse response, @RequestParam("mobile") String mobile) {
        //获取登录验证码
        String code = verifyUserService.registerSMSCode(response,mobile);
        return Result.success("验证码已发送，请查收！");
    }

    /**
     * 用户短信注册接口
     * @param response
     * @param User 封装类 传的数据命名 按bean中变量名字命名
     * @return
     */
    @RequestMapping("/do_Regiser")
    @ResponseBody
    public Result<String> doRegister(HttpServletResponse response, @Valid User user,@RequestParam("code")String code) {
        logger.info(user.toString());
        return Result.success(verifyUserService.register(response, user,code));
    }




}
