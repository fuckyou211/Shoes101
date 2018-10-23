package com.shoes101.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.mapper.PropertyvalueMapper;
import com.shoes101.mapper.RushbuyMapper;
import com.shoes101.mapper.ShoesMapper;
import com.shoes101.pojo.Shoespic;
import com.shoes101.redis.PrepareRushKey;
import com.shoes101.redis.RedisService;
import com.shoes101.service.PrepareRushService;
import com.shoes101.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PrepareRushServiceImpl implements PrepareRushService {

    @Autowired
    private RushbuyMapper rushbuyMapper;

    @Autowired
    private PropertyvalueMapper propertyvalueMapper;

    @Autowired
    private ShoesMapper shoesMapper;

    @Autowired
    private RedisService redisService;

    public String getAllRush(){
        List<RushbuyVo> list = rushbuyMapper.getAllRush();

        List<RushbuyVo> before = new ArrayList<>();
        List<RushbuyVo> doing = new ArrayList<>();
        List<RushbuyVo> end = new ArrayList<>();

        for(int i = 0;i < list.size();i++)
        {
            list.get(i).setShoesname(rushbuyMapper.getShoesname(list.get(i).getShoesid()));
            list.get(i).setPicaddress(rushbuyMapper.getPic(list.get(i).getShoesid()));
            System.out.println(rushbuyMapper.getPic(list.get(i).getShoesid()));
        }

        //筛选活动中 已结束 未开始活动
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("当前时间" + dateFormat.format(date));
        for(int i = 0;i < list.size();i++)
        {

            try{
                Date begintime = dateFormat.parse(list.get(i).getBegintime());
                System.out.println(begintime);
                int result = date.compareTo(dateFormat.parse(list.get(i).getBegintime()));
                int result1 = date.compareTo(dateFormat.parse(list.get(i).getEndtime()));

                Date endtime = dateFormat.parse(list.get(i).getEndtime());

                if(result == 1 && result1 == -1)
                {
                    doing.add(list.get(i));
                }
                else if(result1 == 1 && result == 1)
                {
                    end.add(list.get(i));
//                    list.get(i).setStatus("抢购结束");
                }
                else if(result == -1 && result1 == -1)
                {
                    before.add(list.get(i));
//                    list.get(i).setStatus("抢购未开始");
                }
            }catch (ParseException e){
                e.printStackTrace();
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("before",before);
        map.put("doing",doing);
        map.put("end",end);
//        map.put("rushlist",list);
        return JSONObject.toJSONString(map);
    }

    public String getOneRush(int rushbuyid){
        RushbuyVo rushbuyVo = rushbuyMapper.getOneRush(rushbuyid);
        rushbuyVo.setShoesname(rushbuyMapper.getShoesname(rushbuyVo.getShoesid()));
        //获取抢购活动的所有库存
        List<RushSkuAndQtyVo> getSkuandQty = rushbuyMapper.getSkuAndQty(rushbuyid);
        List<RushShoesskuAndPropvnameVo> list = new ArrayList<>();
        List<String> piclist = new ArrayList<>();
        List<String> picskulist = new ArrayList<>();
        for(int i = 0;i < getSkuandQty.size();i++)
        {
            RushShoesskuAndPropvnameVo a = rushbuyMapper.getSoOn(getSkuandQty.get(i).getSkuid());
            RushShoesskuAndPropvnameVo d = new RushShoesskuAndPropvnameVo();
            d.setSkuid(getSkuandQty.get(i).getSkuid());
            d.setRushquantity(getSkuandQty.get(i).getQuantity());
            d.setShoesid(a.getShoesid());
            d.setSkuproperty(a.getSkuproperty());
            list.add(d);
            //每种配色图
            picskulist.add(rushbuyMapper.getSkuPic(getSkuandQty.get(i).getSkuid()));
        }
        //获取大图
        String bigpic = rushbuyMapper.getBigPic(rushbuyVo.getShoesid());

//        ShoesskuAndPropvnameVo spv = new ShoesskuAndPropvnameVo();
//        spv.setShoessku(shoesMapper.getShoesskuForRush(shoesid));
        //属性分析
        for(int i = 0;i < list.size() ;i++)
        {
            String str = list.get(i).getSkuproperty();
            str = str.replace("{", "").replace("}","");
            String[] strs = str.split(",");
            Map<String, String> m = new HashMap<String, String>();
            for(String s:strs){
                String[] ms = s.split(":");
                //m.put(ms[0], ms[1]);
                //如果是颜色
                System.out.println(ms[0]);
                System.out.println(ms[1]);
                if(ms[0].equals("2"))
                {
//                    System.out.println(ms[0]);
                    list.get(i).setColor(propertyvalueMapper.getPropertyvalue(Integer.parseInt(ms[1])));
                    //color.add(propertyvalueMapper.getPropertyvalue(Integer.parseInt(ms[1])));
                }
                //如果是尺码
                if(ms[0].equals("3"))
                {
                    list.get(i).setSize(propertyvalueMapper.getPropertyvalue(Integer.parseInt(ms[1])));
                    //size.add(propertyvalueMapper.getPropertyvalue(Integer.parseInt(ms[1])));
                }
            }
        }

        Map<String, Object> map = new HashMap<>();
        map.put("rushinfo",rushbuyVo);
        map.put("rushlist",list);
        map.put("rushpic",picskulist);
//        for(int i = 0;i < list.size();i++){
////            redisService.set(PrepareRushKey.showRushBuy,"rush_list",rushlist);
//            redisService.set(PrepareRushKey.shoesRush,"" + list.get(i).getSkuid(),list.get(i).getRushquantity());
//        }
//        redisService.set(PrepareRushKey.userlimit,""+rushbuyVo.getRushbuyid(),rushbuyVo.getLimitN());
//        redisService.set(PrepareRushKey.endtime,""+rushbuyVo.getRushbuyid(),rushbuyVo.getEndtime());
        return JSONObject.toJSONString(map);
    }
}
