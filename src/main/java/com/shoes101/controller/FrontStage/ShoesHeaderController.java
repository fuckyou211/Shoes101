package com.shoes101.controller.FrontStage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shoes101.pojo.Propertyvalue;
import com.shoes101.pojo.Shoescatalog;
import com.shoes101.result.Result;
import com.shoes101.service.ShoesHeaderService;
import com.shoes101.service.PropertyService;
import com.shoes101.vo.CatalogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/header")
public class ShoesHeaderController {

    @Autowired
    private ShoesHeaderService shoesHeaderService;
    @Autowired
    private PropertyService propertyService;

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
    @ResponseBody
    public String handleClickNavBarCatalog(Integer catalogId){
        return "";
    }
}
