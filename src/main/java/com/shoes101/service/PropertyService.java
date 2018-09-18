package com.shoes101.service;

import com.shoes101.pojo.Property;
import com.shoes101.pojo.Propertyvalue;

import java.util.List;

public interface PropertyService {

    //获取所有属性
    public List<Property> getAllProperty();

    //验证属性
    public int checkExist(String propertyvalue);

    //新增属性
    public List<Property> addProp1(String propertyname);

    //新增属性 同时加到两张表
    public List<Property> addProp2(int propertyid, String propertyvalue);

    //修改属性名
    public List<Property> updateP(int propertyid,String propertyname);

    //修改属性详细值
    public List<Property> updateProp(String propertyvalue, int propertyid);

    //删除属性 两张表一起删除
    public List<Property> deleteProp(int propertyid);

    //获取详细属性值
    public Propertyvalue getProperty(int propertyid);

    //获取所有详细属性值
    public List<Propertyvalue> getAllPropertyValue();

}
