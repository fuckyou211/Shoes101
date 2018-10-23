package com.shoes101.service;


import com.shoes101.pojo.Property;
import com.shoes101.vo.FGoodsVo;
import com.shoes101.vo.PropertyValueVo;

import java.util.*;

public interface PropertyFilterServie {

    //在 shoesList 中 得出 他们 某 属性下 全部的属性值
    public Set<PropertyValueVo> getGeneralPropertyValue(List<FGoodsVo> shoesList, Integer propertyId);

    //筛选（对于当前页是 在 某分类 下的 ）
    public List<FGoodsVo> filterCatalog(String filterList,Integer catalogId);

    //筛选（对于当前页是在 某 品牌下 的）
    public List<FGoodsVo> filterPropVal(String filterList,Integer propertyValueId,Integer pageCode,Integer size);

    //筛选（对于当前页是在搜索之后的）
    public List<FGoodsVo> filterSearch(String filterList,String value,Integer pageCode,Integer size);

    //筛选（对于当前页是在 某 品牌下 的） 后的商品 数量
    public Integer getCountFilterPropVal(String filterList,Integer propertyValueId);

    //筛选（对于当前页是在搜索之后的） 后的商品  数量
    public Integer getCountFilterSearch(String filterList,String value);
}
