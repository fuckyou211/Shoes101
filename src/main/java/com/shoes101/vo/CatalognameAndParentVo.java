package com.shoes101.vo;

public class CatalognameAndParentVo {

    private String catalogname;
    private int parentid;

    @Override
    public String toString() {
        return "CatalognameAndParentVo{" +
                "catalogname='" + catalogname + '\'' +
                ", parentid=" + parentid +
                '}';
    }

    public String getCatalogname() {
        return catalogname;
    }

    public void setCatalogname(String catalogname) {
        this.catalogname = catalogname;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }
}
