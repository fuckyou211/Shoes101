package com.shoes101.redis;

public class FGoodsKey extends BasePrefix {
    private FGoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static FGoodsKey getGoodsList = new FGoodsKey(60, "gl");
    public static FGoodsKey getGoodsDetail = new FGoodsKey(60, "gd");
    public static FGoodsKey getAllQtyAndSkuId = new FGoodsKey(20,"qs");
}
