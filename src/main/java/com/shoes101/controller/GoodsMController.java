package com.shoes101.controller;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.pojo.Addshoes;
import com.shoes101.result.Result;
import com.shoes101.service.GoodsMService;
import org.hibernate.validator.constraints.EAN;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * 添加商品管理
 */

@Controller
@RequestMapping("/goodsM")
public class GoodsMController {

    private  final Logger logger= LoggerFactory.getLogger(GoodsMController.class) ;

    @Autowired
    GoodsMService goodsMService;

    @RequestMapping("/addShoes")
    public String addShoes(){
        System.out.println("AKAKAKA");
        return "back/addShoes";
    }


    @RequestMapping("/addShoesAjax")
    @ResponseBody
    public Result<String> addShoesInformationAjax(){
        logger.info("addShoesInformationAjax");
       return  Result.success(goodsMService.addShoesInformationAjax());
    //    return  JSONObject.toJSONString("111");
    }

    @RequestMapping("/addShoesForm")
    @ResponseBody
    public Result<String> addShoesForm(HttpServletRequest request,@Valid Addshoes addshoes){
        logger.info("addShoesForm");
        logger.info(JSONObject.toJSONString(addshoes));
        return  Result.success(goodsMService.addShoesForm(addshoes,request));
    }


    @RequestMapping("/shoesCatalogAjax")
    @ResponseBody
    public String shoesCatalogAjax(@RequestParam(name="parentId",required=false) Integer parentId){
        return  goodsMService.shoesCatalogAjax(parentId);
        //    return  JSONObject.toJSONString("111");
    }


    @RequestMapping("/shoesPropertyAjax")
    @ResponseBody
    public String shoesPropertyAjax(@RequestParam(name="propertyId",required=false) Integer propertyId){
        return goodsMService.shoesPropertyAjax(propertyId);
    }

    @RequestMapping("/shoesShowAjax")
    @ResponseBody
    public Result<String> shoesShowAjax(){
        return Result.success(goodsMService.shoesShowAjax());
    }

    @RequestMapping("/editQuantit")
    public String editQuantit(Model model, @RequestParam(name="shoesid",required=false) Integer shoesid){
        model.addAttribute("result",Result.success(goodsMService.editQuantitAjax(shoesid)));
        return "back/editt_shoesQuantity";
    }

    @RequestMapping("/editQuantitAjax")
    @ResponseBody
    public Result<String> editQuantitAjax(@RequestParam(name="shoesid",required=false) Integer shoesid){
        return Result.success(goodsMService.editQuantitAjax(shoesid));
    }



}
