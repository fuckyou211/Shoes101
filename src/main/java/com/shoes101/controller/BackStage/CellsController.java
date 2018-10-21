package com.shoes101.controller.BackStage;


import com.alibaba.fastjson.JSONObject;

import com.shoes101.service.CellsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@RequestMapping("/cells")
@Controller
public class CellsController {

    @Autowired
    private CellsService cellsService;

    @RequestMapping("/jumptocells")
    public String jumptocells()
    {
        return "back/shoesStatistical";
    }

    @RequestMapping("/tocells")
    @ResponseBody
    public String tocells()
    {
//       map.put("slist", cellsService.tocells());
       // System.out.println(map);
    //    return "success";
        return cellsService.tocells();
    }

    @RequestMapping("/getYmsellsAjax")
    @ResponseBody
    public String getYmsells(@RequestParam("year") String year,@RequestParam("month") String month,Map<String,Object> map)
    {
//        map.put("daysell",);
        return cellsService.getYmsells(year,month);
    }

    //一年每月销量 传year
    @RequestMapping("/getAYsellsAjax")
    @ResponseBody
    public String getYsells(@RequestParam("year") String year,Map<String,Object> map)
    {
//        map.put("monsell",);
        return cellsService.getYsells(year);
    }

    //每年销量 不用传
    @RequestMapping("/getEveryYsell")
    @ResponseBody
    public String getEveryYsells(Map<String,Object> map)
    {
//        map.put("ysell",);
        return cellsService.getEveryYsells();
    }

//    //根据年月返回销量
//    @RequestMapping("/getMonthSells")
//    @ResponseBody
//    public
}
