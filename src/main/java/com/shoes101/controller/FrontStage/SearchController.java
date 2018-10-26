package com.shoes101.controller.FrontStage;

import com.alibaba.fastjson.JSON;
import com.shoes101.pojo.Property;
import com.shoes101.result.Result;
import com.shoes101.service.PageSevice;
import com.shoes101.service.PropertyFilterServie;
import com.shoes101.service.PropertyService;
import com.shoes101.service.SearchService;
import com.shoes101.util.ListHandleUtils;
import com.shoes101.vo.FGoodsVo;
import com.shoes101.vo.PropertyValueVo;
import com.shoes101.vo.pageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Controller
public class SearchController {
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertyFilterServie propertyFilterServie;
    @Autowired
    private PageSevice pageSevice;
    @Autowired
    private SearchService searchService;
    //针对首页搜索框
    @RequestMapping("/ShoesShop/Search")
    public String searchForHomePage(String value,HashMap<String,Object> map){
        HashMap<String,Set<PropertyValueVo>> map1 = new HashMap<String,Set<PropertyValueVo>>();
        List<FGoodsVo> list = new ArrayList<>();
        List<FGoodsVo> newList = new ArrayList<>();
        list = searchService.search(value);
        newList = ListHandleUtils.getPartOfList(list,1,4);
        pageBean pb;
        pb = pageSevice.setTopageBean(1,4,newList,list.size());
        List<Property> propertyList = propertyService.getAllProperty();
        for(Property property:propertyList){
            Set<PropertyValueVo> propertyValueSet = propertyFilterServie.getGeneralPropertyValue(list,property.getPropertyid());
            map1.put(property.getPropertyname(),propertyValueSet);
        }
        map.put("pageOfShoes", JSON.toJSONString(pb));
        map.put("propertyFilter",JSON.toJSONString(map1));
        map.put("value",value);
        return "front/shoes-search";
    }

    @RequestMapping("/search")
    @ResponseBody
    public Result search(String value)
    {
        HashMap<String,Set<PropertyValueVo>> map1 = new HashMap<String,Set<PropertyValueVo>>();
        HashMap<String,Object> map = new HashMap<>();
        List<FGoodsVo> list = new ArrayList<>();
        List<FGoodsVo> newList = new ArrayList<>();
        list = searchService.search(value);
        newList = ListHandleUtils.getPartOfList(list,1,4);
        pageBean pb;
        pb = pageSevice.setTopageBean(1,4,newList,list.size());
        List<Property> propertyList = propertyService.getAllProperty();
        for(Property property:propertyList){
            Set<PropertyValueVo> propertyValueSet = propertyFilterServie.getGeneralPropertyValue(list,property.getPropertyid());
            map1.put(property.getPropertyname(),propertyValueSet);
        }
        pb = pageSevice.setTopageBean(1,4,list,searchService.searchByNameCount(value));
        map.put("pageOfShoes", pb);
        map.put("propertyFilter",map1);
        return Result.success(map);
    }
}
