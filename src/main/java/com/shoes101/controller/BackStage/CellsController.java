package com.shoes101.controller.BackStage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shoes101.result.Result;
import com.shoes101.service.CellsService;
import com.shoes101.vo.DaySellVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@RequestMapping("/cells")
@Controller
public class CellsController {

    @Autowired
    private CellsService cellsService;

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
        map.put("daysell",cellsService.getYmsells(year,month));
        return JSONObject.toJSONString(map);
    }

//    //根据年月返回销量
//    @RequestMapping("/getMonthSells")
//    @ResponseBody
//    public
}
