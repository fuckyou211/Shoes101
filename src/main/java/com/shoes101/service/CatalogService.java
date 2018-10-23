package com.shoes101.service;

import com.shoes101.pojo.Shoes;
import com.shoes101.pojo.Shoescatalog;
import com.shoes101.result.Result;

import java.util.List;

public interface CatalogService
{

    /*//获得某节点的所有叶子节点
    List<Shoescatalog> getLeafList(List<Shoescatalog> leafList, Shoescatalog shoescatalog);*/
    //获得某节点的儿子节点
    List<Shoescatalog> getChildList(Integer catalogId);
    //添加分类
    Integer addCatalog(Shoescatalog catalog);
    //删除某节点及以下分类节点的id
    Integer deleteCatalog(Integer catalogId);
    //更新
    Integer updateCatalog(Shoescatalog catalog);
    //页面上方分类路径最后一个
    Shoescatalog catalogPathLast(Integer parentId);
    //获得父级节点
    List<Shoescatalog> getParentCatalog(Integer parentId);
    //某节点下的所有节点
    List<Shoescatalog> nextCatalogList(List<Shoescatalog> nextList, Shoescatalog shoescatalog);
}
