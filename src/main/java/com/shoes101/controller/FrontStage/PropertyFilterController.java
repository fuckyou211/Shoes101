package com.shoes101.controller.FrontStage;

import com.shoes101.result.Result;
import com.shoes101.service.PageSevice;
import com.shoes101.service.PropertyFilterServie;
import com.shoes101.util.ListHandleUtils;
import com.shoes101.vo.FGoodsVo;
import com.shoes101.vo.pageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PropertyFilterController {

    @Autowired
    private PropertyFilterServie propertyFilterServie;
    @Autowired
    private PageSevice pageSevice;
    //当页面是分类下的或某品牌下的商品时调用
    @RequestMapping("/propertyFilter")
    @ResponseBody
    public Result propertyFilter(String filterList,Integer catalogId,Integer propertyValueId){
        //System.out.println(filterList);
        pageBean pb = null;
        List<FGoodsVo> list;
        //如果当前展示的是某分类下的商品
        if(catalogId!=null){
             list = propertyFilterServie.filterCatalog(filterList,catalogId);
            List<FGoodsVo> newList = ListHandleUtils.getPartOfList(list,1,16);
            pb = pageSevice.setTopageBean(1,16,newList,list.size());
        }
        //如果当前展示的是某品牌下的商品
        else if(propertyValueId!=null){
            list = propertyFilterServie.filterPropVal(filterList,propertyValueId,1,16);
            pb = pageSevice.setTopageBean(1,16,list,propertyFilterServie.getCountFilterPropVal(filterList,propertyValueId));
        }
        return Result.success(pb);
    }
    //当页面是搜索页下的
    @RequestMapping("/propertySearchFilter")
    @ResponseBody
    public Result propertySearchFilter(String value,String filterList){
        List<FGoodsVo> list = propertyFilterServie.filterSearch(filterList,value,1,16);
        pageBean pb = pageSevice.setTopageBean(1,16,list,propertyFilterServie.getCountFilterSearch(filterList,value));
        return Result.success(pb);
    }
}
