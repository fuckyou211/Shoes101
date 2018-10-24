package com.shoes101.service;

import com.shoes101.pojo.User;
import com.shoes101.result.Result;
import com.shoes101.vo.RushOrderVo;

import javax.servlet.http.HttpServletRequest;


public interface RushOrderService {
    public Result<String> CreatRushOrder(HttpServletRequest request, User user, RushOrderVo rushOrderVo);

    public Result<String> QueryRushOrder(HttpServletRequest request, User user, RushOrderVo rushOrderVo);

    }
