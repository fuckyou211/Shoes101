package com.shoes101.service;


import com.shoes101.pojo.Property;
import com.shoes101.vo.FGoodsVo;
import com.shoes101.vo.PropertyValueVo;

import java.util.*;

public interface PropertyFilterServie {

    public Property getPropertyByName(String name);

    public Set<PropertyValueVo> getGeneralPropertyValue(List<FGoodsVo> shoesList, Integer propertyId);
}
