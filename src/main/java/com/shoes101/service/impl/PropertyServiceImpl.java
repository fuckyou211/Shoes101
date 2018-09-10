package com.shoes101.service.impl;

import com.shoes101.mapper.PropertyMapper;
import com.shoes101.mapper.PropertyvalueMapper;
import com.shoes101.pojo.Property;
import com.shoes101.pojo.Propertyvalue;
import com.shoes101.service.PropertyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyMapper propertyMapper;

    @Autowired
    PropertyvalueMapper propertyvalueMapper;


    //获取所有属性
    public List<Property> getAllProperty()
    {
        return propertyMapper.selectAll();
    }

    //验证属性
    public int checkExist(String propertyvalue)
    {
        if(propertyvalueMapper.findIfExist(propertyvalue) == 1)
            return 0;
        return 1;
    }

    //新增属性 同时加到两张表 验证是否存在
    public List<Property> addProp(String propertyname, String propertyvalue)
    {
            propertyMapper.inserttwo(propertyname);
            propertyvalueMapper.inserttwo(propertyvalue,propertyMapper.getLastId());

            return propertyMapper.selectAll();

    }

    //修改属性
    public List<Property> updateProp(String propertyvalue, int propertyid)
    {
        int result = propertyvalueMapper.updateProp(propertyvalue,propertyid);
        System.out.println(result);
        return propertyMapper.selectAll();
    }

    //删除属性 两张表一起删除
    public List<Property> deleteProp(int propertyid)
    {
       int result1 = propertyvalueMapper.deleteProp(propertyid);
       int result2 = propertyMapper.deleteByPrimaryKey(propertyid);
        return propertyMapper.selectAll();
    }

    //查看详情
    public Propertyvalue getPropertyValue(int propertyid)
    {
        return propertyvalueMapper.selectByPrimaryKey(propertyid);
    }

    //获取详细属性值
    public Propertyvalue getProperty(int propertyid)
    {
        return propertyvalueMapper.selectByPrimaryKey(propertyid);
    }
}
