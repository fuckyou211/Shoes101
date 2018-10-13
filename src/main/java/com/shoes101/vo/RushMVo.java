package com.shoes101.vo;

/**
 * 后台抢购设置用Vo
 */
public class RushMVo {

    private int shoesid;

    private String shoespic;

    private String shoesname;

    private int shoessku;

    private int price;

    @Override
    public String toString() {
        return "RushMVo{" +
                "shoesid=" + shoesid +
                ", shoespic='" + shoespic + '\'' +
                ", shoesname='" + shoesname + '\'' +
                ", shoessku=" + shoessku +
                ", price=" + price +
                '}';
    }

    public int getShoesid() {
        return shoesid;
    }

    public void setShoesid(int shoesid) {
        this.shoesid = shoesid;
    }

    public String getShoespic() {
        return shoespic;
    }

    public void setShoespic(String shoespic) {
        this.shoespic = shoespic;
    }

    public String getShoesname() {
        return shoesname;
    }

    public void setShoesname(String shoesname) {
        this.shoesname = shoesname;
    }

    public int getShoessku() {
        return shoessku;
    }

    public void setShoessku(int shoessku) {
        this.shoessku = shoessku;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
