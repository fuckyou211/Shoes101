package com.shoes101.controller.FrontStage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shoes101.access.UserContext;
import com.shoes101.pojo.*;
import com.shoes101.redis.RedisService;
import com.shoes101.result.Result;
import com.shoes101.service.*;
import com.shoes101.util.ListHandleUtils;
import com.shoes101.vo.CatalogInfoVo;
import com.shoes101.vo.FGoodsVo;
import com.shoes101.vo.PropertyValueVo;
import com.shoes101.vo.pageBean;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
public class ShoesHeaderController {

    @Autowired
    private ShoesHeaderService shoesHeaderService;
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private CatalogService catalogService;
    @Autowired
    private PropertyFilterServie propertyFilterServie;
    @Autowired
    private PageSevice pageSevice;
    @Autowired
    private RedisService redisService;

    //获得分类信息
    @ResponseBody
    @RequestMapping("/header/getCatalogInfo/{level}")
    public Result<Map<String,List<CatalogInfoVo>>> getNavBarInfo(@PathVariable("level") Integer level, String catalogNameInfo, Integer parentId){

        JSONArray catalogName = JSON.parseArray(catalogNameInfo);
        System.out.println(catalogNameInfo);
        Map<String,List<CatalogInfoVo>> map = new HashMap<String, List<CatalogInfoVo>>();
        for(int i = 0;i<catalogName.size();i++){
            Shoescatalog shoescatalog = shoesHeaderService.selectByNameAndParentId(parentId, (String) catalogName.get(i));
            List<CatalogInfoVo> list = shoesHeaderService.getCatalogInfo(shoescatalog,level);
            map.put((String) catalogName.get(i),list);
        }
        return  Result.success( map);
    }
    //获得品牌信息
    @RequestMapping("/header/getBrandInfo")
    @ResponseBody
    public Result<List<Propertyvalue>> getBrandInfo(){
        String propertyName = "品牌";
        Integer propertyId = propertyService.getPropertyIdByPropertyName(propertyName);
        //System.out.println(propertyService.getProperty(propertyId));
        return Result.success(propertyService.getProperty(propertyId));
    }
    @RequestMapping("/header/toShoes-header")
    public String toShoesHeader(HashMap<String,Object> hm){
        return "/front/shoes-header";
    }

    //点击导航栏触发
    @RequestMapping("/ShoesShop/shoes-list")
    public String handleClickNavBar(@RequestParam(name="catalogId",required = false) Integer catalogId, HashMap<String,Object> map,
                                           @RequestParam(name="propertyValueId",required=false) Integer propertyValueId){

        HashMap<String,Set<PropertyValueVo>> map1 = new HashMap<String,Set<PropertyValueVo>>();
        List<FGoodsVo> list = new ArrayList<FGoodsVo>();
        pageBean pb = new pageBean();
        List<FGoodsVo> newList = new ArrayList<>();
        if(catalogId!=null){
            //获得此catalogId下的所有鞋
            list =  shoesHeaderService.listUnderCatalog(catalogId);
            newList = ListHandleUtils.getPartOfList(list,1,4);
        }
        else if(propertyValueId!=null){
            list = shoesHeaderService.listUnderProVal(propertyValueId);
            newList = ListHandleUtils.getPartOfList(list,1,4);
        }
        pb = pageSevice.setTopageBean(1,4,newList,list.size());
        List<Property> propertyList = propertyService.getAllProperty();
        for(Property property:propertyList){
            Set<PropertyValueVo> propertyValueSet = propertyFilterServie.getGeneralPropertyValue(list,property.getPropertyid());
            map1.put(property.getPropertyname(),propertyValueSet);
        }
        //System.out.println(pb);
        map.put("pageOfShoes",JSON.toJSONString(pb));
        map.put("propertyFilter",JSON.toJSONString(map1));
        return "front/shoes-list";
    }
    //拿用户数据
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(){
        return UserContext.getUser();
    }
}
