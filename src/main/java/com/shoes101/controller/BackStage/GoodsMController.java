package com.shoes101.controller.BackStage;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.pojo.Addshoes;
import com.shoes101.result.Result;
import com.shoes101.service.GoodsFService;
import com.shoes101.service.GoodsMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * 添加商品管理
 */

@Controller
@RequestMapping("/goodsM")
public class GoodsMController {

    private  final Logger logger= LoggerFactory.getLogger(GoodsMController.class) ;

    @Autowired
    GoodsMService goodsMService;

    @Autowired
    GoodsFService goodsFService;

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


    @RequestMapping("/setQuantitAjax")
    @ResponseBody
    public Result<String> setQuantitAjax(@RequestParam(name="quantity",required=false) List<Integer> quantity,@RequestParam(name="skuid",required=false) List<Integer> skuid) {
        logger.info(JSONObject.toJSONString(skuid));
        logger.info(JSONObject.toJSONString(quantity));
        return  Result.success(goodsMService.setQuantitAjax(skuid,quantity));
    }

    @RequestMapping("/setisdropoffAjax")
    @ResponseBody
    public Result<String> setisdropoffAjax(@RequestParam(name="shoesid",required=false) Integer shoesid,@RequestParam(name="isdropoff",required=false) String isdropoff) {
        logger.info("shoesid:"+shoesid+"  isdropoff:"+isdropoff);
        return  Result.success(goodsMService.setisdropoffAjax(shoesid,isdropoff));
    }

    /**
     * 跳转商品详情
     */
    @RequestMapping("/todetail")
    public String todetail()
    {
        return "back/show_shoes";
    }

    /**
     * 获取商品详情 传入商品Id
     * show_shoes.html
     */
    @RequestMapping("/getDetailAjax")
    @ResponseBody
    public Result<String> todetail(@RequestParam("shoesid") int shoesid,Model model)
    {
        // , HashMap<String,Object> map
        //map.put("detail",goodsFService.todetail(shoesid));
        //model.addAttribute("detail",goodsFService.todetail(shoesid));
        logger.info("shoesid:"+shoesid);
        return Result.success(goodsFService.todetail(shoesid));
    }

    /**
     * 根据id 颜色 尺码 返回相应库存
     */
    @RequestMapping("/getQtyAjax")
    @ResponseBody
    public int getQuantity(@RequestParam("shoesid") int shoesid,@RequestParam("colorid") String colorid,@RequestParam("sizeid") String sizeid)
    {
        logger.info("Qty:" + goodsFService.getQty(shoesid,colorid,sizeid));
        int qty = goodsFService.getQty(shoesid,colorid,sizeid);
        return qty;
    }

}
