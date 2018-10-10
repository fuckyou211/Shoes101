package com.shoes101.service;

import com.shoes101.pojo.User;
import com.shoes101.vo.LoginCodeVo;
import com.shoes101.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

public interface VerifyUserService {

    public String login(HttpServletResponse response, LoginVo loginVo);
    public String loginCode(HttpServletResponse response, LoginCodeVo loginCodeVo);
    public String loginSMSCode(HttpServletResponse response, String mobile);

    public User getByToken(HttpServletResponse response, String token);
}
