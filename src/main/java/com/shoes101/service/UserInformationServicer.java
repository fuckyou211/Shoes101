package com.shoes101.service;

import com.shoes101.pojo.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface UserInformationServicer {
    public Map<String, Object> UserMyAccount(HttpServletRequest request, User user);
}