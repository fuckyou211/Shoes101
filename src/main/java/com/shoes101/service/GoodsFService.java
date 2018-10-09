package com.shoes101.service;


import com.shoes101.vo.SkuIdAndQtyVo;

/**
 * 前台商品Service
 */
public interface GoodsFService {

    //商品列表
    public String tolist();

    //根据商品id获取商品详情
    public String todetail(int shoesid);

    //由id 颜色 尺码 返回库存数量
    public int getQty(int shoesid,String colorid,String sizeid);

    //由id 颜色 尺码 返回库存数量 供前台使用 返回skuid和库存数量
    public SkuIdAndQtyVo getQty2(int shoesid, String colorid, String sizeid);

}
