package com.shoes101.redis;

public class FGoodsKey extends BasePrefix {
    private FGoodsKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static FGoodsKey getGoodsList = new FGoodsKey(60, "gl");
    public static FGoodsKey getGoodsDetail = new FGoodsKey(60, "gd");
    public static FGoodsKey getQtyAndSkuId = new FGoodsKey(20,"qs");
    public static FGoodsKey getGoodsListCatalog = new FGoodsKey(60,"glCatalog");
    public static FGoodsKey getGoodsListProVal = new FGoodsKey(60,"glProVal");
    public static FGoodsKey getGoodsListSearch = new FGoodsKey(60,"value");
}
