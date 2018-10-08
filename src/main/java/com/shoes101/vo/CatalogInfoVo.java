package com.shoes101.vo;

import com.shoes101.pojo.Shoescatalog;

import java.util.List;
import java.util.Map;

public class CatalogInfoVo {

    /*//所有的分类的信息封装list
    private List<Map<String,Object>> allCatalogInfo;

    //父节点的封装分类信息list
    private List<Map<String,Object>> parentCatalogInfo;

    public List<Map<String, Object>> getAllCatalogInfo() {
        return allCatalogInfo;
    }

    public void setAllCatalogInfo(List<Map<String, Object>> allCatalogInfo) {
        this.allCatalogInfo = allCatalogInfo;
    }

    public List<Map<String, Object>> getParentCatalogInfo() {
        return parentCatalogInfo;
    }

    public void setParentCatalogInfo(List<Map<String, Object>> parentCatalogInfo) {
        this.parentCatalogInfo = parentCatalogInfo;
    }

    @Override
    public String toString() {
        return "CatalogInfoVo{" +
                "allCatalogInfo=" + allCatalogInfo +
                ", parentCatalogInfo=" + parentCatalogInfo +
                '}';
    }*/

    //---------------------分割线-----------------------------------------
    //上述代码作废，下面为10.1新版本


    private Shoescatalog  shoescatalog;//自身分类节点数据

    private Map<String,Object> childMapData;//儿子节点map

    private Integer isLeaf;//这个节点是否是叶子节点，1是叶子节点

    public Shoescatalog getShoescatalog() {
        return shoescatalog;
    }

    public void setShoescatalog(Shoescatalog shoescatalog) {
        this.shoescatalog = shoescatalog;
    }

    public Map<String, Object> getChildMapData() {
        return childMapData;
    }

    public void setChildMapData(Map<String, Object> childMapData) {
        this.childMapData = childMapData;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
    }

    @Override
    public String toString() {
        return "CatalogInfoVo{" +
                "shoescatalog=" + shoescatalog +
                ", childMapData=" + childMapData +
                ", isLeaf=" + isLeaf +
                '}';
    }
}
