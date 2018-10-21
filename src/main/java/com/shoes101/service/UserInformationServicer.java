package com.shoes101.service;

import com.shoes101.pojo.User;
import com.shoes101.result.Result;
import com.shoes101.vo.UserImformationVo;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserInformationServicer {
    public Map<String, Object> UserMyAccount(HttpServletRequest request, User user);
    public String UpdateInformation(HttpServletRequest request,User user, UserImformationVo userImformationVo);
    public Result<String> UpdatePassword(HttpServletRequest request, User user, String password, String setpassword);

}