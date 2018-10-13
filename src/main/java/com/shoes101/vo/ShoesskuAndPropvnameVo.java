package com.shoes101.vo;

import com.shoes101.pojo.Shoessku;

import java.util.List;

/**
 * 库存和属性Vo 抢购管理-填写库存
 */
public class ShoesskuAndPropvnameVo {

    private Integer skuid;

    private Integer shoesid;

    private String skuproperty;

    private Integer quantity;

    private String color;

    private String size;

    @Override
    public String toString() {
        return "ShoesskuAndPropvnameVo{" +
                "skuid=" + skuid +
                ", shoesid=" + shoesid +
                ", skuproperty='" + skuproperty + '\'' +
                ", quantity=" + quantity +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                '}';
    }

    public Integer getSkuid() {
        return skuid;
    }

    public void setSkuid(Integer skuid) {
        this.skuid = skuid;
    }

    public Integer getShoesid() {
        return shoesid;
    }

    public void setShoesid(Integer shoesid) {
        this.shoesid = shoesid;
    }

    public String getSkuproperty() {
        return skuproperty;
    }

    public void setSkuproperty(String skuproperty) {
        this.skuproperty = skuproperty;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
