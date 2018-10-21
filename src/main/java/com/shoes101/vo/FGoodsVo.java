package com.shoes101.vo;

import com.shoes101.pojo.Shoespic;

import java.util.List;

/**
 * 前台商品展示 一个选项包含商品id 商品名 商品图片 商品图片单独查询 以列表形式存储
 */
public class FGoodsVo {

    private int shoesid;
    private String shoesname;
    private double price;
    private String adddate;
    private List<String> pics;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getShoesid() {
        return shoesid;
    }

    public void setShoesid(int shoesid) {
        this.shoesid = shoesid;
    }

    public String getShoesname() {
        return shoesname;
    }

    public void setShoesname(String shoesname) {
        this.shoesname = shoesname;
    }

    public List<String> getPics() {
        return pics;
    }

    public void setPics(List<String> pics) {
        this.pics = pics;
    }

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }

    @Override
    public String toString() {
        return "FGoodsVo{" +
                "shoesid=" + shoesid +
                ", shoesname='" + shoesname + '\'' +
                ", price=" + price +
                ", adddate='" + adddate + '\'' +
                ", pics=" + pics +
                '}';
    }
}
