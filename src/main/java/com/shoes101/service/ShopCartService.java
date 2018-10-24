package com.shoes101.service;

import com.shoes101.pojo.Shopcartdetails;
import com.shoes101.vo.ShopCartVo;

import java.util.List;

public interface ShopCartService {


    /**
     * 购物车模块
     * 大概需要四个接口
     * 1.用户登录后，获取用户的购物车 getShopCart
     * 2.添加商品到购物车  addToCart
     * 3.编辑购物车，主要是修改数量  editShopCart
     * 4.删除，把商品从购物车中拿掉  removeFormCart
     *
     *
     */
    // 先添加
    /**
     * 按照数据库设计的方式，一个用户只能有一台购物车
     * 所以要添加的时候，先获取他的购物车
     * 然后把获取到的购物车详情与其关联
     * 最后返回数据给前台
     *
     * 需要接受的数据
     * skuid  quantity userid(这个可以慢点）
     * 就用Shopcartdetails接受
     * 要返回的数据
     *
     * list<skuid quantity price shoesname picid  color  size >
     *
     *
     */
    //添加购物车
    List<ShopCartVo> addShopCart(ShopCartVo shopCartVo);

    //获取购物车
    List<ShopCartVo> getUserShopCart();

    //编辑购物车
    List<ShopCartVo> editShopCart(ShopCartVo shopCartVo);

    //删除购物车中的商品
    List<ShopCartVo> removeFormCart(List<ShopCartVo> shopCartVos);



}
