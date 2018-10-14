package com.shoes101.mapper;

import com.shoes101.pojo.Rushbuy;
import com.shoes101.vo.RushbuyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RushbuyMapper {
    int deleteByPrimaryKey(Integer rushbuyid);

    int insert(Rushbuy record);

    Rushbuy selectByPrimaryKey(Integer rushbuyid);

    List<Rushbuy> selectAll();

    int updateByPrimaryKey(Rushbuy record);

    //根据rushbuy中的商品id获取商品名
    public List<RushbuyVo> getAllFromRb();

    //获取图片
    String getPic(@Param("shoesid") int shoesid);

    //获取最新插入到rushbuy的id
    int getLateId();

    //删除抢购活动
    int deleteRush(@Param("rushbuyid") int rushbuyid);

    //删除对应的库存
    int deleteRushsku(@Param("rushbuyid") int rushbuyid);



}