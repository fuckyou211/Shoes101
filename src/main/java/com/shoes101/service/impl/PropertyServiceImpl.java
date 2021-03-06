package com.shoes101.service.impl;

import com.shoes101.mapper.PropertyMapper;
import com.shoes101.mapper.PropertyvalueMapper;
import com.shoes101.pojo.Property;
import com.shoes101.pojo.Propertyvalue;
import com.shoes101.redis.PropertyKey;
import com.shoes101.redis.RedisService;
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
    @Autowired
    private RedisService redisService;

    //获取所有属性
    public List<Property> getAllProperty()
    {
        List<Property> list = redisService.get(PropertyKey.getPropertyList,"",true,Property.class);
        System.out.println("--------------"+list);
        if(list==null){
            list = propertyMapper.selectAll();
            redisService.set(PropertyKey.getPropertyList,"",list);
        }
        return list;
    }

    //根据id获得属性和属性名
    public Property getPropertyAndName(int propertyid)
    {
        return propertyMapper.selectByPrimaryKey(propertyid);
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
    public List<Propertyvalue> updateProp(String propertyvalue, int propertyvalueid)
    {
        int result = propertyvalueMapper.updateProp(propertyvalue,propertyvalueid);
        //根据pvid寻找pid
        int propertyid = propertyvalueMapper.delAndGetPropid(propertyvalueid);
        System.out.println(result);
        return propertyvalueMapper.selectpv(propertyid);
    }

    //删除属性 两张表一起删除
    public List<Property> deleteProp(int propertyid)
    {
        int result1 = propertyMapper.deleteByPrimaryKey(propertyid);
        int result2 = propertyvalueMapper.deleteProp(propertyid);
        System.out.println("删除属性名结果：" + result1 + "删除相关属性值结果：" + result2);
        return propertyMapper.selectAll();
    }

    //删除属性值
    public List<Propertyvalue> deletePropV(int propertyvalueid)
    {
        //根据删除的pvid获取pid
        int propertyid = propertyvalueMapper.delAndGetPropid(propertyvalueid);
        int result1 = propertyvalueMapper.deleteProp(propertyvalueid);
        return propertyvalueMapper.selectpv(propertyid);
//      int result2 = propertyMapper.deleteByPrimaryKey(propertyid);
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


    //根据属性名找属性id
    @Override
    public Integer getPropertyIdByPropertyName(String propertyName) {
        Property property = propertyMapper.findIfExist(propertyName);
        if(property!=null){
            return property.getPropertyid();
        }
        else{
            return null;
        }
    }
}
