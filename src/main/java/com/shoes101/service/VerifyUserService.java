package com.shoes101.service;

import com.shoes101.pojo.User;
import com.shoes101.vo.LoginCodeVo;
import com.shoes101.vo.LoginVo;
import com.shoes101.vo.ResetPasswordVo;
import com.shoes101.vo.UserVo;

import javax.servlet.http.HttpServletResponse;

public interface VerifyUserService {

    public String login(HttpServletResponse response, LoginVo loginVo);
    public String loginCode(HttpServletResponse response, LoginCodeVo loginCodeVo);
    public String loginSMSCode(HttpServletResponse response, String mobile);
    public String registerSMSCode(HttpServletResponse response, String mobile);
    public String restPasswordSMSCode(HttpServletResponse response, String mobile);
    public String restPassword(HttpServletResponse response, ResetPasswordVo resetPasswordVo);
    public String register(HttpServletResponse response, UserVo userVo, String code);

    public User getByToken(HttpServletResponse response, String token);
}
