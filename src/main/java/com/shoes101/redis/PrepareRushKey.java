package com.shoes101.redis;

/**
 * RushBuy前期准备
 * 1.显示抢购活动
 * 2.点击获取抢购活动
 * 3.获取抢购后的对应库存
 * 4.用户限额
 * 5.限制时间
 */
public class PrepareRushKey extends BasePrefix {
    private PrepareRushKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    //1
    public static PrepareRushKey showRushBuy = new PrepareRushKey(300,"sr");
    //2
    public static PrepareRushKey selectOneRush = new PrepareRushKey(300,"so");
    //3
    public static PrepareRushKey shoesRush = new PrepareRushKey(300,"sk");
    //4
    public static PrepareRushKey userlimit = new PrepareRushKey(300,"ul");
    //5
    public static PrepareRushKey endtime = new PrepareRushKey(300,"et");
//    public static FGoodsKey getGoodsList = new FGoodsKey(60, "gl");
//    public static FGoodsKey getGoodsDetail = new FGoodsKey(60, "gd");
//    public static FGoodsKey getQtyAndSkuId = new FGoodsKey(20,"qs");
}
