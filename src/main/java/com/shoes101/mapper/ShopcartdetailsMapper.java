package com.shoes101.mapper;

import com.shoes101.pojo.Shopcartdetails;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ShopcartdetailsMapper {
    int deleteByPrimaryKey(Integer scdid);

    int insert(Shopcartdetails record);

    Shopcartdetails selectByPrimaryKey(Integer scdid);

    List<Shopcartdetails> selectAll();

    int updateByPrimaryKey(Shopcartdetails record);

    @Select("select * from shopcartdetails where scid=#{scid}")
    List<Shopcartdetails> selectByscid(@Param("scid") Integer scid);

    //根据skuid获得shoesid
    int selectShoesidBySkuid(Integer skuid);

    //根基skuid获得颜色尺码属性
    String selectSkupropertyBySkuid(Integer skuid);

    //根据shoesid获取shoesname
    String selectShoesnameByShoesid(Integer shoesid);

    //根据属性值id返回属性值名
    String selectPropValByPropValId(Integer propertyvalueid);

    //根据skuid获取对应的颜色图片地址
    String selectcolorpicByid(Integer skuid);
}