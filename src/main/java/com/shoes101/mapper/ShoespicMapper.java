package com.shoes101.mapper;

import com.shoes101.pojo.Shoespic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShoespicMapper {
    int deleteByPrimaryKey(Integer picid);

    int insert(Shoespic record);

    Shoespic selectByPrimaryKey(Integer picid);

    List<Shoespic> selectAll();

    int updateByPrimaryKey(Shoespic record);

    @Select( "select a.picaddress from shoespic as a where a.shoesid = #{shoesid} limit 1 ")
    Shoespic selectByshoesid(@Param("shoesid")Integer shoesid);

}