package com.shoes101.controller;

import com.shoes101.pojo.Shoescatalog;
import com.shoes101.result.Result;
import com.shoes101.service.NavBarService;
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
public class NavBarController {

    @Autowired
    private NavBarService navBarService;

    @RequestMapping("/getCatalogInfo/{level}")
    @ResponseBody
    public Result<Map<String,List<CatalogInfoVo>>> getNavBarInfo( @PathVariable("level") Integer level,String catalogName[],Integer parentId){

        Map<String,List<CatalogInfoVo>> map = new HashMap<String, List<CatalogInfoVo>>();
        for(int i = 0;i<catalogName.length;i++){
            Shoescatalog shoescatalog = navBarService.selectByNameAndParentId(parentId,catalogName[i]);
            List<CatalogInfoVo> list = navBarService.getCatalogInfo(shoescatalog,level);
            map.put(catalogName[i],list);
        }
        return  Result.success( map);
    }

}
