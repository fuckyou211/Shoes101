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

    //获得某分类下所有的鞋（FGoodsVo）
    public List<FGoodsVo> listUnderCatalog(Integer catalogId);

    //获得某节点的所有叶子节点的
    List<Shoescatalog> getLeafList(List<Shoescatalog> leafList, Shoescatalog shoescatalog);

    //获得某propertyValueId下所有的list
    public List<FGoodsVo> listUnderProVal(Integer propertyValueId);

    //获得某propertyValueId下的list（分页）
    public List<FGoodsVo> listUnderProVal(Integer propertyValueId,Integer pageCode,Integer size);
    //根据品牌获得相应的鞋的数量
    public Integer getFGoodsVoCountByPvId(Integer propertyValueId);
}
