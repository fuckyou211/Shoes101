package com.shoes101.controller;

import com.shoes101.result.Result;
import com.shoes101.service.NavBarService;
import com.shoes101.vo.CatalogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class NavBarController {

    @Autowired
    private NavBarService navBarService;
    @RequestMapping("/getCatalogInfo")
    @ResponseBody
    public Result<CatalogInfoVo> getCatalogInfo(){
        return Result.success( navBarService.getCatalogInfo());
    }
}
