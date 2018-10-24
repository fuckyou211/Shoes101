package com.shoes101.controller.FrontStage;


import com.shoes101.redis.RedisService;
import com.shoes101.redis.UserKey;
import com.shoes101.result.CodeMsg;
import com.shoes101.result.Result;
import com.shoes101.service.VerifyUserService;
import com.shoes101.vo.LoginCodeVo;
import com.shoes101.vo.LoginVo;
import com.shoes101.vo.ResetPasswordVo;
import com.shoes101.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private  final Logger logger= LoggerFactory.getLogger(LoginController.class) ;
	
	@Autowired
    VerifyUserService verifyUserService;


	@Autowired
    RedisService redisService;

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
    	return Result.success("验证码已发送，请查收！");
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
        return Result.success("验证码已发送，请查收！");
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
     * @param传的数据命名按bean中变量名字命名
     * @return
     */
    @RequestMapping("/do_Regiser")
    @ResponseBody
    public Result<String> doRegister(HttpServletResponse response, @Valid UserVo user, @RequestParam("code")String code) {
        logger.info(user.toString());
        return Result.success(verifyUserService.register(response, user,code));
    }

    /**
     * 修改密码短信码接口
     * @param response
     * @param mobile  手机号码
     * @return
             */
    @RequestMapping("/resetPasswordSMSCode")
    @ResponseBody
    public Result<String> resetPasswordSMSCode(HttpServletResponse response, @RequestParam("mobile") String mobile) {
        //获取登录验证码
        String code = verifyUserService.restPasswordSMSCode(response,mobile);
        return Result.success("验证码已发送，请查收！");
    }

    /**
     * 修改密码短信码接口
     * @param response
     * @param
     * @return
     */
    @RequestMapping("/resetPassword")
    @ResponseBody
    public Result<String> resetPassword(HttpServletResponse response, @Valid ResetPasswordVo resetPasswordVo) {
        //获取登录验证码
        return Result.success(verifyUserService.restPassword(response,resetPasswordVo));
    }

    @RequestMapping("/logout")
    @ResponseBody
    public Result<Boolean> logout(HttpServletRequest request,HttpServletResponse response){
        logger.info("申请退出！");

        Cookie[] cookies = request.getCookies();

        String tk = null;
        logger.info("Cookies:"+cookies);
        for (Cookie cookie: cookies) {
            logger.info("cookiename={}, cookievalue={}", cookie.getName(), cookie.getValue());
            if(cookie.getName().equals("token")){
                tk = cookie.getValue();
                logger.info("token--->"+tk);
                cookie.setMaxAge(-122111);
            }
        }

        if(tk == null){
            Result.error(CodeMsg.SERVER_ERROR);
        }

        redisService.delete(UserKey.token, tk);

        return Result.success(true);

    }


}
