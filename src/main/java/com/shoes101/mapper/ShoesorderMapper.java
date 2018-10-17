package com.shoes101.mapper;

import com.shoes101.pojo.Shoesorder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoesorderMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(Shoesorder record);

    Shoesorder selectByPrimaryKey(Integer orderid);

    List<Shoesorder> selectAll();

    int updateByPrimaryKey(Shoesorder record);

    //修改订单状态
    public int changeState(@Param("state") int state, @Param("orderid") int orderid);

    //申请退货
    public int iscancel(@Param("orderid")int orderid,@Param("cancel") int cancel);

    //根据skuid获取价格
    public int getPriceFromSku(@Param("skuid") int skuid);

    //根据用户id查询订单
    public List<Shoesorder> getOrderByUserId(@Param("userid") int userid);
}