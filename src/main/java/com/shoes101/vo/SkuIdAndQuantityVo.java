package com.shoes101.vo;

/**
 * 填写订单用Vo List  传入ID和数量
 */

public class SkuIdAndQuantityVo {

    private int skuid;
    private int quantity;

    @Override
    public String toString() {
        return "SkuIdAndQuantityVo{" +
                "skuid=" + skuid +
                ", quantity=" + quantity +
                '}';
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
}
