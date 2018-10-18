package com.shoes101.controller.BackStage;

import com.shoes101.result.Result;
import com.shoes101.service.RushMService;
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

    //跳转到新增抢购
    @RequestMapping("/torush")
    public String jumpToRush(Map<String, String> map)
    {
        String rushlist = rushMService.getShoesForRush();
        map.put("rushlist",rushlist);
        return "back/publishRushShoes";
    }

    //跳转到抢购管理
    @RequestMapping("/torushManager")
    public String turushManager(Map<String, String> map)
    {
        String rblist = rushMService.getAllRush();
        map.put("rblist",rblist);
        return "back/manager_rushShoes";
    }

    //存入rushbuy Ajax 确定抢购活动
    @RequestMapping("/confirmRushAjax")
    @ResponseBody
    public Result<String> confirmRushAjax(@RequestParam("shoesid") int shoesid, @RequestParam("price") int price, @RequestParam("shoessku") int shoessku,
                                          @RequestParam("starttime") String starttime, @RequestParam("endtime") String endtime)
    {
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        System.out.println("开始的时间:" + starttime);
        System.out.println("结束的时间:" + endtime);
        return Result.success(rushMService.getShoesskuForRush(shoesid,price,shoessku,starttime,endtime));
    }

//    data:{"skuidlist":skuidlist,"shoesid":shoesid,"quantitylist":quantitylist}
    //存入rushsku Ajax  确定抢购活动的库存等
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

    //删除活动 Ajax
    @RequestMapping("/deleteRushAjax")
    @ResponseBody
    public String deleteRushAjax(@RequestParam("rushbuyid") int rushbuyid,Map<String, String> map)
    {

        return rushMService.deleteRush(rushbuyid);
    }


    //存入rushbuy Ajax 确定抢购活动
    @RequestMapping("/confirmChangeRushAjax")
    @ResponseBody
    public String confirmChangeRushAjax(@RequestParam("shoesid") int shoesid, @RequestParam("price") int price, @RequestParam("shoessku") int shoessku,
                                          @RequestParam("starttime") Date starttime, @RequestParam("endtime") Date endtime,@RequestParam("rushbuyid") int rushbuyid)
    {
        System.out.println("这里的" + shoessku);
        return rushMService.updateShoesskuForRush(shoesid,price,shoessku,starttime,endtime,rushbuyid);
    }

    //    data:{"skuidlist":skuidlist,"shoesid":shoesid,"quantitylist":quantitylist}
    //存入rushsku Ajax  确定抢购活动的库存等
    @RequestMapping("sendInChangeRushAjax")
    @ResponseBody
    public Result<String> sendInChangeRushAjax(@RequestParam("skuidlist") List<Integer> skuidlist,@RequestParam("shoesid") int shoesid,
                                         @RequestParam("quantitylist") List<Integer> quantitylist,@RequestParam("rushbuyid") int rushbuyid)
    {
        System.out.println(skuidlist);
        System.out.println(skuidlist);
        System.out.println(shoesid);
        System.out.println(quantitylist);
        return Result.success(rushMService.sendInUpdateRushsku(skuidlist,shoesid,quantitylist,rushbuyid));

    }


    //转换日期
    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,true));

    }


}
