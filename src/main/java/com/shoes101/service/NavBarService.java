package com.shoes101.service;


import com.shoes101.pojo.Shoescatalog;
import com.shoes101.vo.CatalogInfoVo;

import java.util.List;
import java.util.Map;

public interface NavBarService {

    //某节点下数level层内的封装数据信息
    public List<CatalogInfoVo> getCatalogInfo(Shoescatalog shoescatalog, Integer level);

    //根据名字和parentid找到节点
    public Shoescatalog selectByNameAndParentId(Integer parentId,String catalogName);

}
