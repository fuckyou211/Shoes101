package com.shoes101.vo;

import com.shoes101.pojo.Property;
import com.shoes101.pojo.Propertyvalue;
import com.shoes101.pojo.Shoessku;
import org.springframework.web.util.HtmlUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台商品详情
 * 商品名
 * 库存
 * 价格
 * 描述
 * 图片 按颜色分图片
 * 可选尺码
 * 根据商品id获取商品名称、价格、库存等
 * 颜色图片根据库存寻找对应
 * 根据商品属性获取尺码
 * (3, 1, 2, 3) 商品1尺码为X
 * (4, 1, 2, 4) 商品1尺码为XL
 * 根据商品id查找所有有关的属性名和属性值-根据属性名区分颜色和尺码-抽取属性详细侄女
 */

public class FDetailsVo {

    //商品id
    private int shoesid;
    //商品 名称
    private String shoesname;
    //SKU 库存
    private int quantity;
    //SKU 实价
    private double price;
    //SKU 标价
    private double ticketprice;
    //商品 详情 10.9更新 放回进这里处理
    private String shoesdetails;
    //10.10更新 商品标签
    private String label;

    //根据商品id获取商品、库存、价格、详情
    //获取所有的sku 根据skuid获得对应鞋子颜色的图片和尺码


    @Override
    public String toString() {
        return "FDetailsVo{" +
                "shoesid=" + shoesid +
                ", shoesname='" + shoesname + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", ticketprice=" + ticketprice +
                ", shoesdetails='" + shoesdetails + '\'' +
                ", label='" + label + '\'' +
                '}';
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getShoesdetails() {
        return shoesdetails;
    }

    public void setShoesdetails(String shoesdetails) {
        this.shoesdetails = shoesdetails;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTicketprice() {
        return ticketprice;
    }

    public void setTicketprice(double ticketprice) {
        this.ticketprice = ticketprice;
    }
//
//    public static void main(String args[])
//    {
//        String str = "[1:1,2:3]";
//        String[] strs = str.split(",");
//        System.out.println(strs);
//        Map<String,String> m = new HashMap<String, String>();
//        for(String s:strs){
//            String[] ms = s.split(":");
//            m.put(ms[0], ms[1]);
//        }
//        System.out.println(m);
//    }

}
