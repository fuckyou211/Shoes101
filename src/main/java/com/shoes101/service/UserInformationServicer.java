package com.shoes101.service;

import com.shoes101.pojo.User;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

public interface UserInformationServicer {

    public Model UserMyAccount(HttpServletRequest request, Model model, User user);
}
