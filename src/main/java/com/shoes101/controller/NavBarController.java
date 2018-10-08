package com.shoes101.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.shoes101.pojo.Shoescatalog;
import com.shoes101.result.Result;
import com.shoes101.service.NavBarService;
import com.shoes101.vo.CatalogInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class NavBarController {

    @Autowired
    private NavBarService navBarService;

    @ResponseBody
    @RequestMapping("/getCatalogInfo/{level}")
    public Result<Map<String,List<CatalogInfoVo>>> getNavBarInfo(@PathVariable("level") Integer level, String catalogNameInfo, Integer parentId){

        JSONArray catalogName = JSON.parseArray(catalogNameInfo);
        System.out.println(catalogName);
        Map<String,List<CatalogInfoVo>> map = new HashMap<String, List<CatalogInfoVo>>();
        for(int i = 0;i<catalogName.size();i++){
            Shoescatalog shoescatalog = navBarService.selectByNameAndParentId(parentId, (String) catalogName.get(i));
            List<CatalogInfoVo> list = navBarService.getCatalogInfo(shoescatalog,level);
            map.put((String) catalogName.get(i),list);
        }
        return  Result.success( map);
    }

}
