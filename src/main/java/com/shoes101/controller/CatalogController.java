package com.shoes101.controller;

import com.alibaba.fastjson.JSON;
import com.shoes101.pojo.Shoescatalog;
import com.shoes101.result.Result;
import com.shoes101.service.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/Catalog")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;


    //分类管理
    @RequestMapping("/tomanagerClassify")
    public String tomanagerClassify(HashMap<String,Object> map)
    {
        List<Shoescatalog> list = catalogService.getParentCatalog(0);
        String catalogList = JSON.toJSONString(list);
        map.put("catalogParentList", Result.success(catalogList));
        return "/back/manager_classify";
    }
    /**
     * 获得分类子节点
     * @param catalogId
     * @return
     */
    @RequestMapping("/getChildCatalog")
    @ResponseBody
    public Result<String> getChildCatalg(Integer catalogId){
        String catalogList = JSON.toJSONString(catalogService.getChildList(catalogId));
        return Result.success(catalogList);
    }

    /**
     * 添加分类
     */
    @RequestMapping("/addCatalog")
    @ResponseBody
    public Result<Integer> addCatalog(Shoescatalog catalog){ ;
        return Result.success(catalogService.addCatalog(catalog));
    }

    /**
     * 删除分类
     */
    @RequestMapping("/deleteCatalog")
    @ResponseBody
    public Result<Integer> deleteCatalog(Integer catalogId)
    {

        return Result.success(catalogService.deleteCatalog(catalogId));
    }

    /**
     * 修改分类
     */
    @RequestMapping("/updataCatalog")
    @ResponseBody
    public Result<Integer> updataCatalog(Shoescatalog catalog){

        return Result.success(catalogService.updateCatalog(catalog));
    }


    /**
     * 获取根分类
     */
    @RequestMapping("/getRootCatalog")
    @ResponseBody
    public Result<String> getRootCatalog(){
        List<Shoescatalog> list = catalogService.getParentCatalog(0);
        String catalogList = JSON.toJSONString(list);
        return Result.success(catalogList);
    }

}
