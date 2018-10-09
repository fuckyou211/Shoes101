package com.shoes101.vo;

/**
 * skuid和库存qtyVo
 * 用于显示库存以及点击购买时直接根据skuid跳转订单
 */
public class SkuIdAndQtyVo {

    private int skuid;
    private int quantity;


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

    @Override
    public String toString() {
        return "SkuIdAndQtyVo{" +
                "skuid=" + skuid +
                ", quantity=" + quantity +
                '}';
    }
}
