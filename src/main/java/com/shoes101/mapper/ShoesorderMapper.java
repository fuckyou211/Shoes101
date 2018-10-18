package com.shoes101.mapper;

import com.shoes101.pojo.Shoesorder;
import com.shoes101.vo.ShoesorderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoesorderMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(Shoesorder record);

    Shoesorder selectByPrimaryKey(Integer orderid);

    List<ShoesorderVo> selectAll();

    int updateByPrimaryKey(Shoesorder record);

    //修改订单状态
    public int changeState(@Param("state") int state, @Param("orderid") int orderid);

    //申请退货
    public int iscancel(@Param("orderid")int orderid,@Param("cancel") int cancel);

    //根据skuid获取价格
    public int getPriceFromSku(@Param("skuid") int skuid);

    //根据用户id查询订单
    public List<Shoesorder> getOrderByUserId(@Param("userid") int userid);

    //根据skuid虎丘库存
    public int getQuantityFromSku(@Param("skuid") int skuid);

    //减库存
    public int setNewSku(@Param("skuid") int skuid,@Param("sku") int sku);

    //根据条件返回列表
    public List<ShoesorderVo> getbyitem(@Param("validity")int validity,@Param("cancel")int cancel, @Param("state")int state);

    //发货或者退款
    public int sendOrBack(@Param("orderid") int orderid,@Param("validity") int validity,@Param("cancel") int cancel,@Param("state") int state);
}