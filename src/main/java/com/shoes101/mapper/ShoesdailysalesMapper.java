package com.shoes101.mapper;

import com.shoes101.pojo.Shoesdailysales;
import com.shoes101.vo.DaySellVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShoesdailysalesMapper {
    int deleteByPrimaryKey(Integer sdid);

    int insert(Shoesdailysales record);

    Shoesdailysales selectByPrimaryKey(Integer sdid);

    List<Shoesdailysales> selectAll();

    int updateByPrimaryKey(Shoesdailysales record);

    //获取年月交易金额
    public int getmonthsell(@Param("ym1") String ym1,@Param("ym2")String ym2);

    //获取年月订单成交量
    public int getmonthorder(@Param("ym1") String ym1,@Param("ym2") String ym2);

    //获取当月注册的用户数量
    public int getmonthregistuser(@Param("ym1") String ym1,@Param("ym2") String ym2);

    //获取当年当月的销量
    public List<DaySellVo> getDaySell(@Param("ym1") String ym1);
}