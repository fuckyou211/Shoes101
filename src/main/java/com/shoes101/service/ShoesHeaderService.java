package com.shoes101.service;


import com.shoes101.pojo.Propertyvalue;
import com.shoes101.pojo.Shoes;
import com.shoes101.pojo.Shoescatalog;
import com.shoes101.vo.CatalogInfoVo;
import com.shoes101.vo.FGoodsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ShoesHeaderService {

    //某节点下数level层内的封装数据信息
    public List<CatalogInfoVo> getCatalogInfo(Shoescatalog shoescatalog, Integer level);

    //根据名字和parentid找到节点
    public Shoescatalog selectByNameAndParentId(Integer parentId,String catalogName);

    //初始化catalogInfo
    public Map<String,List<CatalogInfoVo>> initCatalogInfo();

    public List<FGoodsVo> listUnderCatalog(Integer catalogId);

    //获得某节点的所有叶子节点
    List<Shoescatalog> getLeafList(List<Shoescatalog> leafList, Shoescatalog shoescatalog);

    //处理点击品牌的处理
    public List<FGoodsVo> listUnderProVal(Integer propertyValueId);

    //根据品牌获得相应的鞋的数量
    public Integer getFGoodsVoCountByPvId(Integer propertyValueId);
}
