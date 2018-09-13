package com.shoes101.controller;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.service.GoodsMService;
import org.hibernate.validator.constraints.EAN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 添加商品管理
 */

@Controller
@RequestMapping("/goodsM")
public class GoodsMController {

    @Autowired
    GoodsMService goodsMService;

    @RequestMapping("/addShoes")
    public String addShoes(){
        System.out.println("AKAKAKA");
        return "back/addShoes";
    }


    @RequestMapping("/addShoesAjax")
    @ResponseBody
    public String addShoesInformationAjax(){

       return  goodsMService.addShoesInformationAjax();
    //    return  JSONObject.toJSONString("111");
    }

    @RequestMapping("/shoesCatalogAjax")
    @ResponseBody
    public String shoesCatalogAjax(@RequestParam(name="parentId",required=false) Integer parentId){

        return  goodsMService.shoesCatalogAjax(parentId);
        //    return  JSONObject.toJSONString("111");
    }



}
