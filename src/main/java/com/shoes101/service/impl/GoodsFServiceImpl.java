package com.shoes101.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.mapper.PropertyMapper;
import com.shoes101.mapper.PropertyvalueMapper;
import com.shoes101.mapper.ShoesMapper;
import com.shoes101.pojo.Shoespic;
import com.shoes101.pojo.Splink;
import com.shoes101.vo.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class GoodsFServiceImpl implements com.shoes101.service.GoodsFService {

    private final Logger logger = LoggerFactory.getLogger(GoodsFServiceImpl.class);

    @Autowired
    private ShoesMapper shoesMapper;

    @Autowired
    private PropertyvalueMapper propertyvalueMapper;

    @Autowired
    private PropertyMapper propertyMapper;


    //商品列表
    @Override
    public String tolist()
    {
//        System.out.println("1111");
        List<FGoodsVo> list = shoesMapper.getAllShoesIdAndNameAndPrice();
        System.out.println(list.size());
        for(int i = 0;i < list.size();i++)
        {
//            System.out.println("1111111");
            list.get(i).setPics(shoesMapper.getAllPicById(list.get(i).getShoesid()));

        }
        Map<String,Object> map = new HashMap<>();
        map.put("GoodsList",list);
        logger.info(JSONObject.toJSONString(map));
        return JSONObject.toJSONString(map);
    }

    //根据商品id获取商品详情
    //10.9更新 前台后台区分1 2 1前台
    public String todetail(int shoesid)
    {

        //获取商品名、库存、价格、描述
        FDetailsVo fDetailsVo = shoesMapper.getDetails(shoesid);

        //10.10 更新 引入PropAndPropvVo
        List<PropAndPropvVo> propAndPropvVos = new ArrayList<>();

        //10.10更新 获取鞋子的所有大图
        List<String> shoespics = shoesMapper.getAllPic(shoesid);

        //10.8 更新 引入 ColAndPicVo
        List<ColAndPicVo> colAndPicVos = new ArrayList<>();

//        System.out.println("A");
        //根据商品Id查找属性关系表
        List<Splink> sp = shoesMapper.getPidById(shoesid);
//        System.out.println("B");
        //根据尺码属性（3）搜索出所有的鞋码的属性名编号存好属性值
        List<String> colorlist = new ArrayList<>();



        //10.8 废弃SizeList 并入SizeAndIdVo
        List<SizeAndIdVo> sizeAndIdVos = new ArrayList<>();
//        List<String> sizelist = new ArrayList<>();
//        //存取商品的图片 10.8更新 废除该List 并入ColAndPicVo
//        List<String> colorpic = new ArrayList<>();

        String colorstr = "2:";//模糊查询 匹配颜色
        for(int i = 0;i < sp.size();i++)
        {
//            System.out.println("pid" + sp.get(i).getPropertyid());
//            System.out.println("pvid" + sp.get(i).getPropertyvalueid());
            //遍历 如果这是尺码(3)
            if(sp.get(i).getPropertyid().equals(3))
            {
                SizeAndIdVo demo = new SizeAndIdVo();
                demo.setSizeid(sp.get(i).getPropertyvalueid());
                demo.setSize(propertyvalueMapper.getPropertyvalue(sp.get(i).getPropertyvalueid()));
                sizeAndIdVos.add(demo);
//                sizelist.add(propertyvalueMapper.getPropertyvalue(sp.get(i).getPropertyvalueid()));
//                System.out.println("C");
            }
            //如果是颜色(2)
            else if(sp.get(i).getPropertyid().equals(2))
            {
               colorlist.add(propertyvalueMapper.getPropertyvalue(sp.get(i).getPropertyvalueid()));
//                System.out.println("C");
            }
            //如果是其他属性 获取其名字和属性
            else{
                PropAndPropvVo demo = new PropAndPropvVo();
                demo.setProperty(propertyMapper.getPropname(sp.get(i).getPropertyid()));
                demo.setPropertyvalue(propertyvalueMapper.getPropertyvalue(sp.get(i).getPropertyvalueid()));
//                System.out.println("property:" + demo.getProperty());
//                System.out.println("propertyvalue" + demo.getPropertyvalue());
                propAndPropvVos.add(demo);
            }
        }
        //根据商品id和颜色属性获取商品的skuid 用于下一步根据skuid获取到第一个商品图片 商品详情页的颜色小图片显示
        //10.8更新 新增颜色与图片VO 用于放置颜色图片与shoes-detail中指针放置图片显示颜色名
        //遍历 一个个颜色去找
        for(int i = 0;i < colorlist.size();i++)
        {
            ColAndPicVo demo = new ColAndPicVo();
            //'%3:#{color}%'
            //10.4更新 根据搜索到的字符串获取属性值id 再进行查找skuid
            int pvid = propertyvalueMapper.getIdByValue(colorlist.get(i).toString());
            String pvidtostr = String.valueOf(pvid);
            String str = "'%" + colorstr + pvidtostr + "%'";
            System.out.println(str);
            System.out.println(shoesid);
            //获取skuid
            int skuid = shoesMapper.getTheSkuid(shoesid,str);
//            System.out.println("D");
            demo.setColorpic(shoesMapper.getColorPicForDetail(skuid));
            demo.setColor(colorlist.get(i).toString());
            demo.setColorid(pvid);
            colAndPicVos.add(demo);
//            colorpic.add(shoesMapper.getColorPicForDetail(skuid));
//            System.out.println("E");
        }

        //10.9 商品描述由String 转 JSON 解决转义缺少反斜杠问题
        String jsonDetail = JSONObject.toJSONString(shoesMapper.getShoesDetail(shoesid));
        fDetailsVo.setShoesdetails(shoesMapper.getShoesDetail(shoesid));

//        System.out.println("F");
        Map<String,Object> map = new HashMap<>();
        map.put("bigpics",shoespics);
        map.put("propertys",propAndPropvVos);
        //map.put("shoesdetails",jsonDetail);
        map.put("details",fDetailsVo);
        map.put("sizelist",sizeAndIdVos);
        //map.put("colorlist",colorlist);
        map.put("colorpicandcolor",colAndPicVos);
        logger.info(JSONObject.toJSONString(map));
//        System.out.println("G");
        return JSONObject.toJSONString(map);

    }

    //由id 颜色 尺码 返回库存数量 供后台使用
    //10.10 可能只点颜色或者只点尺码 判断有无空
    public int getQty(int shoesid,String colorid,String sizeid)
    {
        //10.10 如果两者之一为空 2颜色 3尺码
        if(colorid.equals("")||sizeid.equals(""))
        {
            if(colorid.equals(""))
            {
                String cdn = "'%3:" + sizeid + "%'";
                return shoesMapper.calQtyWithoutOne(shoesid,cdn);
            }
            if(sizeid.equals(""))
            {
                String cdn = "'%2:" + colorid + "%'";
                return shoesMapper.calQtyWithoutOne(shoesid,cdn);
            }

        }
        //组成搜索字符串
        String str = "{2:"+ colorid + ',' + "3:" + sizeid + '}';
        String condition = '"' + str + '"';
        System.out.println(condition);
        return shoesMapper.getQty(shoesid,condition);


    }

    //由id 颜色 尺码 返回库存数量 供前台使用 返回skuid和库存数量
    public SkuIdAndQtyVo getQty2(int shoesid, String colorid, String sizeid)
    {
        if(colorid.equals("")||sizeid.equals(""))
        {
            if(colorid.equals(""))
            {
                String cdn = "'%3:" + sizeid + "%'";
                SkuIdAndQtyVo demo = new SkuIdAndQtyVo();
                demo.setQuantity(shoesMapper.getQty3(shoesid,cdn));
                demo.setSkuid(-1);
                return demo;
            }
            if(sizeid.equals(""))
            {
                String cdn = "'%2:" + colorid + "%'";
                SkuIdAndQtyVo demo = new SkuIdAndQtyVo();
                demo.setQuantity(shoesMapper.getQty3(shoesid,cdn));
                demo.setSkuid(-1);
                return demo;
            }

        }
        String str = "{2:"+ colorid + ',' + "3:" + sizeid + '}';
        String condition = '"' + str + '"';
        System.out.println(condition);
        return shoesMapper.getQty2(shoesid,condition);
    }

}
