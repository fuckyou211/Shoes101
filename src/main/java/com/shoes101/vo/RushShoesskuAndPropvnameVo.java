package com.shoes101.vo;

/**
 * 抢购属性
 */
public class RushShoesskuAndPropvnameVo {

    private Integer skuid;

    private Integer shoesid;

    private String skuproperty;

    private String color;

    private String size;

    private int rushquantity;

    public int getRushquantity() {
        return rushquantity;
    }

    public void setRushquantity(int rushquantity) {
        this.rushquantity = rushquantity;
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
