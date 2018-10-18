package com.shoes101.controller.FrontStage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shoes101.access.UserContext;
import com.shoes101.pojo.*;
import com.shoes101.result.Result;
import com.shoes101.service.*;
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
@RequestMapping("/header")
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
    @ResponseBody
    @RequestMapping("/getCatalogInfo/{level}")
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
    @RequestMapping("/getBrandInfo")
    @ResponseBody
    public Result<List<Propertyvalue>> getBrandInfo(){
        String propertyName = "品牌";
        Integer propertyId = propertyService.getPropertyIdByPropertyName(propertyName);
        System.out.println(propertyService.getProperty(propertyId));
        return Result.success(propertyService.getProperty(propertyId));
    }
    @RequestMapping("/toShoes-header")
    public String toShoesHeader(HashMap<String,Object> hm){
        return "/front/shoes-header";
    }

    @RequestMapping("/handleClickNavBarCatalog")
    public String handleClickNavBarCatalog(@RequestParam("catalogId") Integer catalogId, HashMap<String,Object> map){
        HashMap<String,Set<PropertyValueVo>> map1 = new HashMap<String,Set<PropertyValueVo>>();
        //获得此catalogId下的所有鞋
       List<FGoodsVo> list =  shoesHeaderService.handleClickNavBarCatalog(catalogId);
        List<FGoodsVo> newList;
        if(list.size()>=16)
           newList = list.subList(0,15);
       else
           newList = list;
       pageBean pb = pageSevice.setTopageBean(1,16,newList,list.size());
       map.put("pageOfShoes",JSON.toJSONString(pb));
      // System.out.println(list);
        List<Property> propertyList = propertyService.getAllProperty();
        for(Property property:propertyList){
            Set<PropertyValueVo> propertyValueSet = propertyFilterServie.getGeneralPropertyValue(list,property.getPropertyid());
            map1.put(property.getPropertyname(),propertyValueSet);
        }
        System.out.println("----------"+map1);
        map.put("propertyFilter",JSON.toJSONString(map1));
        return "/front/shoes-list";
    }

    /*@RequestMapping("/handleClickNavBrand")
    public String handleClickNavBrand(){

    }*/

    //拿用户数据
    @RequestMapping("/getUser")
    @ResponseBody
    public User getUser(){
        return UserContext.getUser();
    }
}
