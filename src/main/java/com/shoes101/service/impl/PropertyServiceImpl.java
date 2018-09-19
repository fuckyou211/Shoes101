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

    //验证属性值
    public int checkExist(String propertyvalue)
    {
        if(propertyvalueMapper.findIfExist(propertyvalue) == null)
            return 0;
        return 1;
    }

    //验证属性名
    public int checkExistP(String propertyname)
    {
        if(propertyMapper.findIfExist(propertyname) == null)
            return 0;
        return 1;
    }

    //新增属性
    public List<Property> addProp1(String propertyname)
    {
        propertyMapper.inserttwo(propertyname);
        return propertyMapper.selectAll();
    }

    //新增属性值
    public List<Propertyvalue> addPropv(int propertyid,String propertyvalue)
    {
        propertyvalueMapper.addPropv(propertyid,propertyvalue);

        return propertyvalueMapper.selectpv(propertyid);
    }

    //新增属性3 同时加到两张表 验证是否存在
    public List<Property> addProp2(int propertyid, String propertyvalue)
    {

        Propertyvalue pv = new Propertyvalue();
        pv.setPropertyId(propertyid);
        pv.setPropertyvalue(propertyvalue);
        propertyvalueMapper.insert(pv);
//            propertyvalueMapper.inserttwo(propertyvalue,propertyMapper.getLastId());//旧版本是同时向两张表插入相关值 返回最新变更的id

        return propertyMapper.selectAll();

    }

    //修改属性名
    public List<Property> updateP(int propertyid,String propertyname)
    {
        int result = propertyMapper.updateP(propertyid,propertyname);
        System.out.println(result);

        return propertyMapper.selectAll();

    }


    //修改属性详细值
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
    public List<Propertyvalue> getProperty(int propertyid)
    {
        return propertyvalueMapper.selectpv(propertyid);
    }

    //获取所有详细属性值
    public List<Propertyvalue> getAllPropertyValue()
    {
        return propertyvalueMapper.selectAll();
    }

    //根据Id获取属性名
    public String getPropname(int propertyid)
    {
        return propertyMapper.getPropname(propertyid);
    }
}
