package com.shoes101.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.shoes101.mapper.PropertyvalueMapper;
import com.shoes101.mapper.RushbuyMapper;
import com.shoes101.mapper.ShoesMapper;
import com.shoes101.pojo.Rushbuy;
import com.shoes101.pojo.Shoessku;
import com.shoes101.service.RushMService;
import com.shoes101.vo.RushMVo;
import com.shoes101.vo.ShoesskuAndPropvnameVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RushMServiceImpl implements RushMService {

    private final Logger logger= LoggerFactory.getLogger(RushMServiceImpl.class) ;

    @Autowired
    private ShoesMapper shoesMapper;

    @Autowired
    private RushbuyMapper rushbuyMapper;

    @Autowired
    private PropertyvalueMapper propertyvalueMapper;

    public String getShoesForRush()
    {
        //获取商品id 图片 名称
        //获取原价 总库存
        List<RushMVo> list = shoesMapper.getShoesForRush();
        Map<String,Object> map = new HashMap<>();
        map.put("rushlist",list);
        logger.info(JSONObject.toJSONString(map));
        return JSONObject.toJSONString(map);
    }

    public String getShoesskuForRush(int shoesid, int price, int shoessku, Date starttime, Date endtime)
    {
        List<String> color = new ArrayList<>();
        List<String> size = new ArrayList<>();

        Rushbuy rb = new Rushbuy();
        rb.setShoesid(shoesid);
        rb.setRbprice(price);
        rb.setRbamount(shoessku);
        rb.setBegintime(starttime);
        rb.setEndtime(endtime);
        rushbuyMapper.insert(rb);


        List<ShoesskuAndPropvnameVo> list = shoesMapper.getShoesskuForRush(shoesid);
//        ShoesskuAndPropvnameVo spv = new ShoesskuAndPropvnameVo();
//        spv.setShoessku(shoesMapper.getShoesskuForRush(shoesid));
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
                    System.out.println(ms[0]);
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
//        spv.setColor(color);
//        spv.setSize(size);
        //List<Shoessku> list = shoesMapper.getShoesskuForRush(shoesid);
        Map<String,Object> map = new HashMap<>();
        map.put("rushskulist",list);
        logger.info(JSONObject.toJSONString(map));
        return JSONObject.toJSONString(map);
    }

    public String sendInRushsku(List<Integer> skuidlist,int shoesid,
                             List<Integer> quantitylist)
    {
        //存入数据库 skuidlist的长度有可能与quantitylist的长度不同（有些库存为0 不允许输入）
        //以quantitylist的长度为准
        for(int i = 0;i < quantitylist.size();i++)
        {
           shoesMapper.sendInRushsku(quantitylist.get(i),shoesid,skuidlist.get(i));
        }
        return "success";
    }

//    public static void main(String args[])
//    {
//
//        String str = "{1:1,2:3}";
//        str = str.replace("{", "").replace("}","");
//        String[] strs = str.split(",");
//        Map<String, String> m = new HashMap<String, String>();
//        for(String s:strs){
//            String[] ms = s.split(":");
//            m.put(ms[0], ms[1]);
//        }
//        System.out.println(m);
//    }



}


