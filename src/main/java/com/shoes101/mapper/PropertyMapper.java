package com.shoes101.mapper;

import com.shoes101.config.mybatis.BaseMapper;
import com.shoes101.pojo.Property;
//import com.sun.xml.internal.rngom.parse.host.Base;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface PropertyMapper{
    int deleteByPrimaryKey(Integer propertyid);

    int insert(Property record);

    Property selectByPrimaryKey(Integer propertyid);

    int updateByPrimaryKey(Property record);


    //获取所有属性
    List<Property> selectAll();

    //增加属性进入两张表中  分开增加  这里增加属性名 不增加属性值值
    public int inserttwo(String propertyname);

    //获取id
    public int getLastId();

    //修改属性值
    public int updateP(@Param("propertyid") int propertyid,@Param("propertyname") String propertyname);

//    //删除属性 两张表一起删除
//    public int deleteProp(@Param("propertyid") int propertyid);

}