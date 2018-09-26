package com.shoes101.mapper;

import com.shoes101.config.mybatis.BaseMapper;
import com.shoes101.pojo.Propertyvalue;
import java.util.List;

import com.shoes101.pojo.Splink;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface PropertyvalueMapper{
    int deleteByPrimaryKey(@Param("propertyvalueid") Integer propertyvalueid, @Param("propertyid") Integer propertyid);

    int insert(Propertyvalue record);

//    Propertyvalue selectByPrimaryKey(@Param("propertyvalueid") Integer propertyvalueid, @Param("propertyid") Integer propertyid);

    List<Propertyvalue> selectAll();

    int updateByPrimaryKey(Propertyvalue record);

    Propertyvalue selectByPrimaryKey(@Param("propertyid") Integer propertyid);

    //获取当前属性名的属性值
    public List<Propertyvalue> selectAllOfPropertyId(@Param("propertyid") int propertyid);

    //根据删除的pvid获取pid
    public int delAndGetPropid(@Param("propertyvalueid") int propertyvalueid);

    //根据属性id查询属性详情
    List<Propertyvalue> selectpv(@Param("propertyid") Integer propertyid);

    //验证是否存在属性
    @Select("select * from propertyvalue where propertyvalue=#{propertyvalue}")
    Propertyvalue findIfExist(@Param("propertyvalue") String propertyvalue);

    //增加属性进入两张表中
    @Insert("insert into propertyvalue(propertyvalue,propertyid) values (#{propertyvalue},#{propertyid})")
    public int inserttwo(@Param("propertyname") String propertyname,@Param("propertyid") int propertyid);

    //修改属性值
    public int updateProp(@Param("propertyvalue") String propertyvalue,@Param("propertyid") int propertyid);

    //删除属性 两张表一起删除
    public int deleteProp(@Param("propertyid") int propertyid);

    //新增属性值
    public int addPropv(@Param("propertyid") int propertyid,@Param("propertyvalue") String propertyvalue);

    //属性值查询
    @Select( "select * from propertyvalue where  propertyid= " +
            "(select a.propertyid from property as a where a.propertyname = #{propertyname} )")
    public List<Propertyvalue> selectColorPropertyvalue(@Param("propertyname") String propertyname);

    //ID属性值查询
    @Select( "select * from propertyvalue where  propertyid= #{propertyId} ")
    public List<Propertyvalue> selectPropertyvalueBypropertyId(@Param("propertyId") Integer propertyId);

    @Select("select c.* from splink a ,propertyvalue c where a.shoesid=#{shoesId} and a.propertyid=#{propertyid} and a.propertyvalueid=c.propertyvalueid " +
            "order by (select b.propertyvalue  from propertyvalue b where a.propertyvalueid=b.propertyvalueid ) ")
    List<Propertyvalue> selectByShoesIdProperty(@Param("shoesId") Integer shoesId, @Param("propertyid") Integer propertyid);


}