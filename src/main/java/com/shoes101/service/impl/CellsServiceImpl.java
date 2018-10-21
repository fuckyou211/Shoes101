package com.shoes101.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.mapper.ShoesdailysalesMapper;
import com.shoes101.mapper.ShoesorderMapper;
import com.shoes101.service.CellsService;
import com.shoes101.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CellsServiceImpl implements CellsService {

    @Autowired
    private ShoesdailysalesMapper shoesdailysalesMapper;

    @Autowired
    private ShoesorderMapper shoesorderMapper;

    public String tocells()
    {
        //获取当前年月
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date yearmon = new Date();
        String yearmonth = sdf.format(yearmon);

        //获取当前年份
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy");
        Date year = new Date();
        String thisyear = sdf2.format(year);

        //获取当前月份
        SimpleDateFormat sdf3 = new SimpleDateFormat("MM");
        Date month = new Date();
        String thismonth = sdf3.format(month);
//        int y = Integer.parseInt(sdf.format(yearmonth));
//        y = y + 1;
//        String year = String.valueOf(y);
        SellsAVo sellsAVo = new SellsAVo();
        //存年月
        sellsAVo.setYearmonth(yearmonth);
//        String year1 = "'" + sdf.format(date) + "-" + "01" + "-" + "01" + "'";
//        String year2 = "'" + year + "-" + "01" + "-" + "01" + "'";
//        System.out.println(year1);
//        System.out.println(year2);

        //字符串拼接  2018-10
        String ym1 = "'" + yearmonth + "-" + "01" + "'";
        //字符串拼接 下个月
        int i = Integer.parseInt(thismonth);
        i = i + 1;
        String nextmonth = String.valueOf(i);
        String ym2 = "'" + thisyear + "-"  + nextmonth + "-" + "01" + "'";
        //字符串拼接 上个月
        int j = Integer.parseInt(thismonth);
        j = j + 1;
        String lastmonth = String.valueOf(i);
        String ym3 = "'" + thisyear + "-"  + nextmonth + "-" + "01" + "'";

        //年月交易额和上升比例
        //1.获取本月交易额和上月交易额
        int thismonthmoney = shoesdailysalesMapper.getmonthsell(ym1,ym2);
        sellsAVo.setMonthmoney(thismonthmoney);
        //上月交易额
        int lastmonthmoney = shoesdailysalesMapper.getmonthsell(ym2,ym3);
        //计算上升比
        int up;
        if(lastmonthmoney == 0)
        {
            up = 100;
        }
        else if(thismonthmoney == 0)
        {
            up = 0;
        }
        else
        {
            up = thismonthmoney/lastmonthmoney * 100;
        }
        sellsAVo.setMoneyup(up);
        System.out.println("上升了" + up + "%");

        //年月订单成交量
        int thismonthorder = shoesdailysalesMapper.getmonthorder(ym1,ym2);
        sellsAVo.setMonthorder(thismonthorder);
        //上月订单量
        int lastmonthorder = shoesdailysalesMapper.getmonthorder(ym2,ym3);
        int up2;
        if(lastmonthorder == 0)
        {
            up2 = 100;
        }
        else if(thismonthorder == 0)
        {
            up2 = 0;
        }
        else
        {
            up2 = thismonthmoney/lastmonthmoney * 100;
        }
        sellsAVo.setOrderup(up2);
        System.out.println("上升了" + up2);


        //年月用户注册量
        int thismonthuser = shoesdailysalesMapper.getmonthregistuser(ym1,ym2);
        sellsAVo.setMonthregistuser(thismonthuser);
        //上月用户
        int lastmonthuser = shoesdailysalesMapper.getmonthregistuser(ym2,ym3);
        int up3;
        if(lastmonthuser == 0)
        {
            up3 = 100;
        }
        else if(thismonthuser == 0)
        {
            up3 = 0;
        }
        else{
            //10.21 修正增长率
            up3 = thismonthmoney - lastmonthmoney/lastmonthmoney * 100;
        }
        sellsAVo.setUserup(up3);
        System.out.println("上升了" + up3);

        //当年当月销量
        //拼接字符串
        String dys = "'" + yearmonth + "%" + "'";
        List<DaySellVo> daySelllist = shoesdailysalesMapper.getDaySell(dys);
        //System.out.println("日销量" + daySelllist);
        for(int z = 0;z < daySelllist.size();z++)
        {
            String newday = daySelllist.get(z).getDayy().substring(8);
            daySelllist.get(z).setDayy(newday);
            System.out.println(daySelllist.get(z).getDayy());

        }
        //10.21 根据需求修改返回形式
        List<Integer> sell = new ArrayList<>();
        List<String> date = new ArrayList<>();
        for(int k = 0;k < daySelllist.size();k++)
        {
            sell.add(daySelllist.get(k).getSell());
            date.add(daySelllist.get(k).getDayy());
        }


        //10.21更新 获取Top10
        List<ShoesidAndSalesVo> sas = shoesorderMapper.getTop();

        Map<String,Object> map = new HashMap<>();
        map.put("slist",sellsAVo);
//        map.put("daysell",daySelllist);
        map.put("sell",sell);
        map.put("date",date);
        System.out.println(JSONObject.toJSONString(map));
        return JSONObject.toJSONString(map);
        //return "back/shoesStatistical";
    }


    public String getYmsells(String year,String month)
    {
        //当年当月销量
        //拼接字符串
//        String dys = "'" + yearmonth + "%" + "'";
        String dys = "'" + year + "-" + month + "%" + "'";
        List<DaySellVo> daySelllist = shoesdailysalesMapper.getDaySell(dys);
        //System.out.println("日销量" + daySelllist);
        for(int z = 0;z < daySelllist.size();z++)
        {
            String newday = daySelllist.get(z).getDayy().substring(8);
            daySelllist.get(z).setDayy(newday);
            System.out.println(daySelllist.get(z).getDayy());
        }

        //10.21 根据需求修改返回形式
        List<Integer> sell = new ArrayList<>();
        List<String> date = new ArrayList<>();
        for(int k = 0;k < daySelllist.size();k++)
        {
            sell.add(daySelllist.get(k).getSell());
            date.add(daySelllist.get(k).getDayy());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("sell",sell);
        map.put("date",date);
        return JSONObject.toJSONString(map);
    }

    public String getYsells(String year)
    {
        //一年每月销量
        List<MonthSellVo> rmonSelllist = shoesdailysalesMapper.getMonthSell();
        List<MonthSellVo> monSelllist = new ArrayList<>();
        for(int i = 0;i < rmonSelllist.size();i++)
        {
            if(rmonSelllist.get(i).getYear().equals(year))
            {
                monSelllist.add(rmonSelllist.get(i));
            }
        }
        //10.21 根据需求修改格式
        List<Integer> sell = new ArrayList<>();
        List<String> date = new ArrayList<>();
        for(int j = 0;j < monSelllist.size();j++)
        {
            sell.add(monSelllist.get(j).getSell());
            date.add(monSelllist.get(j).getMonth());
        }
        Map<String,Object> map = new HashMap<>();
        map.put("sell",sell);
        map.put("date",date);
        return JSONObject.toJSONString(map);
    }

    public String getEveryYsells()
    {
        //10.21 根据需求修改格式
        List<YearSellVo> list = shoesdailysalesMapper.getYearSell();
        List<Integer> sell = new ArrayList<>();
        List<String> date = new ArrayList<>();
        for(int i = 0;i < list.size();i++)
        {
            sell.add(list.get(i).getSells());
            date.add(list.get(i).getYear());
        }
        //每年销量
        Map<String,Object> map = new HashMap<>();
        map.put("sell",sell);
        map.put("date",date);
        return JSONObject.toJSONString(map);
    }
}
