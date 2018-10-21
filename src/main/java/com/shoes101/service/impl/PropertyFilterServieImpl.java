package com.shoes101.service.impl;

import com.shoes101.mapper.PropertyMapper;
import com.shoes101.mapper.PropertyvalueMapper;
import com.shoes101.mapper.SplinkMapper;
import com.shoes101.pojo.Property;
import com.shoes101.pojo.Splink;
import com.shoes101.service.PropertyFilterServie;
import com.shoes101.vo.FGoodsVo;
import com.shoes101.vo.PropertyValueVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
@Transactional
public class PropertyFilterServieImpl implements PropertyFilterServie {

    @Autowired
    PropertyMapper propertyMapper;
    @Autowired
    SplinkMapper splinkMapper;
    @Autowired
    PropertyvalueMapper propertyvalueMapper;

    @Override
    public Property getPropertyByName(String name){
       return propertyMapper.findIfExist(name);
    }
    @Override
    public Set<PropertyValueVo> getGeneralPropertyValue(List<FGoodsVo> shoesList, Integer propertyId){
        Set<PropertyValueVo> propertyvalueSet= new TreeSet<PropertyValueVo>();
        for (FGoodsVo fGoodsVo:shoesList) {
            List<Splink> list = splinkMapper.selectByShoesIdProperty(fGoodsVo.getShoesid(),propertyId);
            //System.out.println(list);
            for(Splink splink:list){
                propertyvalueSet.add(propertyvalueMapper.selectById(splink.getPropertyvalueid()));
            }
        }
        //System.out.println(propertyvalueSet);
        return propertyvalueSet;
    }
}
