package com.shoes101.mapper;

import com.shoes101.pojo.Splink;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SplinkMapper {
    int deleteByPrimaryKey(Integer spid);

    int insert(Splink record);

    Splink selectByPrimaryKey(Integer spid);

    List<Splink> selectAll();

    @Select("select * from splink a where a.shoesid=#{shoesId} and a.propertyid=#{propertyid} " +
            "order by (select b.propertyvalue  from propertyvalue b where a.propertyvalueid=b.propertyvalueid ) ")
    List<Splink> selectByShoesIdProperty(@Param("shoesId") Integer shoesId,@Param("propertyid") Integer propertyid);

    int updateByPrimaryKey(Splink record);
}