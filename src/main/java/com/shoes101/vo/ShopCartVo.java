package com.shoes101.vo;

public class ShopCartVo {

    private int scdid;
    private int skuid;
    private int quantity;//已弃用，改成count
    private int count;
    private double price;
    private double totalprice;
    private String shoesName;
    private String picaddress;//弃用，已改成 colorPic，
    private String colorPic;
    private String color;
    private String size;

    public int getScdid() {
        return scdid;
    }

    public void setScdid(int scdid) {
        this.scdid = scdid;
    }

    public int getSkuid() {
        return skuid;
    }

    public void setSkuid(int skuid) {
        this.skuid = skuid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getShoesName() {
        return shoesName;
    }

    public void setShoesName(String shoesName) {
        this.shoesName = shoesName;
    }

    public String getColorPic() {
        return colorPic;
    }

    public void setColorPic(String colorPic) {
        this.colorPic = colorPic;
    }

    public String getPicaddress() {
        return picaddress;
    }

    public void setPicaddress(String picaddress) {
        this.picaddress = picaddress;
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

    @Override
    public String toString() {
        return "ShopCartVo{" +
                "scdid=" + scdid +
                ", skuid=" + skuid +
                ", count=" + count +
                ", price=" + price +
                ", shoesName='" + shoesName + '\'' +
                ", colorPic='" + colorPic + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
