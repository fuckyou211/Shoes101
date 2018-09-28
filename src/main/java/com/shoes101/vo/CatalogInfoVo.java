package com.shoes101.vo;

import java.util.List;
import java.util.Map;

public class CatalogInfoVo {

    //所有的分类的信息封装list
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
    }
}
