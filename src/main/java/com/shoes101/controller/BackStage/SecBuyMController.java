package com.shoes101.controller.BackStage;

import com.shoes101.result.Result;
import com.shoes101.service.RushMService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 秒杀后台管理
 *
 */
@Controller
@RequestMapping("/rush")
public class SecBuyMController {

    @Autowired
    private RushMService rushMService;

    @RequestMapping("/torush")
    public String jumpToRush(Map<String, String> map)
    {
        String rushlist = rushMService.getShoesForRush();
        map.put("rushlist",rushlist);
        return "back/publishRushShoes";
    }

    @RequestMapping("/confirmRushAjax")
    @ResponseBody
    public Result<String> confirmRushAjax(@RequestParam("shoesid") int shoesid, @RequestParam("price") int price, @RequestParam("shoessku") int shoessku,
                                          @RequestParam("starttime") Date starttime, @RequestParam("endtime") Date endtime)
    {
        return Result.success(rushMService.getShoesskuForRush(shoesid,price,shoessku,starttime,endtime));
    }

//    data:{"skuidlist":skuidlist,"shoesid":shoesid,"quantitylist":quantitylist}
    @RequestMapping("sendInRushAjax")
    @ResponseBody
    public Result<String> sendInRushAjax(@RequestParam("skuidlist") List<Integer> skuidlist,@RequestParam("shoesid") int shoesid,
                                         @RequestParam("quantitylist") List<Integer> quantitylist)
    {
        System.out.println(skuidlist);
        System.out.println(shoesid);
        System.out.println(quantitylist);
        return Result.success(rushMService.sendInRushsku(skuidlist,shoesid,quantitylist));

    }


    //转换日期
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,true));

    }


}
