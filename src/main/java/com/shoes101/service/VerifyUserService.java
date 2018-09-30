package com.shoes101.service;

import com.shoes101.pojo.User;
import com.shoes101.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

public interface VerifyUserService {

    public String login(HttpServletResponse response, LoginVo loginVo);

    public User getByToken(HttpServletResponse response, String token);
}
