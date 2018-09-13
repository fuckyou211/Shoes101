package com.shoes101.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.shoes101.mapper.PropertyMapper;
import com.shoes101.mapper.PropertyvalueMapper;
import com.shoes101.mapper.ShoescatalogMapper;
import com.shoes101.service.GoodsMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class GoodsMServiceImpl implements GoodsMService {

    @Value("${color}")
    private String color;

    @Autowired
    private ShoescatalogMapper shoescatalogMapper;

    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private PropertyvalueMapper propertyvalueMapper;

    @Override
    public String addShoesInformationAjax() {
        Map<String,Object> list=new HashMap<String,Object>();
        list.put("catalog",shoescatalogMapper.findCatalogByParent());
        list.put("property",propertyMapper.selectAll());
        list.put("colorProperty",propertyvalueMapper.selectColorPropertyvalue(color));

        return JSONObject.toJSONString(list);
    }
}
