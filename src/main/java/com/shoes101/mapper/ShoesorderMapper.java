package com.shoes101.mapper;

import com.shoes101.pojo.Shoesorder;
import com.shoes101.vo.ShoesidAndSalesVo;
import com.shoes101.vo.ShoesorderVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShoesorderMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(ShoesorderVo record);

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
    //根据条件返回列表
//    public List<ShoesorderVo> getbyitem(@Param("validity")int validity,@Param("cancel")int cancel, @Param("state")int state);

    //发货或者退款
    public int sendOrBack(@Param("orderid") int orderid,@Param("validity") int validity,@Param("cancel") int cancel,@Param("state") int state);

    @Select("select * from shoesorder as a where a.userid = #{userid} AND a.state=#{state} AND a.validity=1 AND a.cancel=0")
    public List<Shoesorder> getshoesorderByorderidState(@Param("userid") Integer userid,@Param("state") Integer state);

    @Select("select * from shoesorder as a where a.userid = #{userid} AND a.state!= 0 AND a.state !=1 AND a.state!=2 AND a.validity=1 AND a.cancel=0")
    public List<Shoesorder> getshoesorderByorderidState1(@Param("userid") Integer userid,@Param("state") Integer state);

    @Select("select * from shoesorder as a where a.validity = #{validity} AND a.cancel=1")
    public List<Shoesorder> getshoesorderByorderidvalidity(@Param("userid") Integer userid,@Param("validity") Integer validity);



    //获取销量Top10
    public List<ShoesidAndSalesVo> getTop();

    //存销量
    public int setNewSales(@Param("quantity") int quantity,@Param("skuid") int skuid);

}