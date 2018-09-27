package com.shoes101.mapper;

import com.shoes101.pojo.Shoessku;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ShoesskuMapper {
    int deleteByPrimaryKey(Integer skuid);

    int insert(Shoessku record);

    Shoessku selectByPrimaryKey(Integer skuid);

    List<Shoessku> selectAll();

    int updateByPrimaryKey(Shoessku record);

    @Select("select sum(quantity) from shoessku where shoesid = #{shoesid}")
    int selectByshoesIdSum(@Param("shoesid")Integer shoesid);

    @Select("select skuid,quantity from shoessku where shoesid=#{shoeid} and skuproperty=#{skuproperty}")
    Shoessku selectskuproperty(@Param("shoeid") Integer shoesid,@Param("skuproperty") String skuproperty);

    @Update("update  shoessku set quantity=#{quantity} where skuid=#{skuid}")
    int selectquantity(@Param("skuid") Integer skuid,@Param("quantity") Integer quantity);
}