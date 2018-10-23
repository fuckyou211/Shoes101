package com.shoes101.service.impl;

import com.shoes101.mapper.*;
import com.shoes101.pojo.Property;
import com.shoes101.pojo.Shoescatalog;
import com.shoes101.pojo.Splink;
import com.shoes101.redis.FGoodsKey;
import com.shoes101.redis.RedisService;
import com.shoes101.service.PropertyFilterServie;
import com.shoes101.service.ShoesHeaderService;
import com.shoes101.vo.FGoodsVo;
import com.shoes101.vo.PropertyValueVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
@Transactional
public class PropertyFilterServieImpl implements PropertyFilterServie {

    @Autowired
    private PropertyMapper propertyMapper;
    @Autowired
    private SplinkMapper splinkMapper;
    @Autowired
    private PropertyvalueMapper propertyvalueMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ShoesMapper shoesMapper;
    @Autowired
    private ShoesHeaderService shoesHeaderService;
    @Autowired
    private ShoescatalogMapper shoescatalogMapper;

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

    //筛选（对于当前页是 在 某分类 下的 ）
    @Override
    public List<FGoodsVo> filterCatalog(String filterList, Integer catalogId) {
        //取缓存
        List<FGoodsVo> list = redisService.get(FGoodsKey.getGoodsListCatalogFilter,filterList+" "+catalogId,true,FGoodsVo.class);
        if(list == null){
            filterList = "'"+filterList+"'";
            Shoescatalog shoescatalog = shoescatalogMapper.selectByPrimaryKey(catalogId);
            //System.out.println("111"+shoescatalog);
            //取数据库
            List<Shoescatalog> leafList = shoesHeaderService.getLeafList(new ArrayList<Shoescatalog>(),shoescatalog);
            list = new ArrayList<>();
            for(Shoescatalog shoescatalog1 : leafList){
                List<FGoodsVo> tempList = shoesMapper.getFGoodsVoFilterPvCatalog(shoescatalog1.getCatalogid(),filterList);
                System.out.println("tt"+tempList);
                for(int i = 0;i < tempList.size();i++)
                {
                    tempList.get(i).setPics(shoesMapper.getAllPicById(tempList.get(i).getShoesid()));
                }
                for(FGoodsVo fGoodsVo:tempList){
                    list.add(fGoodsVo);
                }
            }
            redisService.set(FGoodsKey.getGoodsListCatalogFilter,filterList+" "+catalogId,list);
        }
        return list;
    }
    //筛选（对于当前页是在 某 品牌下 的）
    @Override
    public List<FGoodsVo> filterPropVal(String filterList, Integer propertyValueId,Integer pageCode,Integer size) {
        Integer start = (pageCode-1) * size;
        filterList = "'"+filterList+"'";
        System.out.println(filterList+propertyValueId+start);
        List<FGoodsVo> list = shoesMapper.getFGoodsVoFilterPvBrand(propertyValueId,start,size,filterList );
        for(FGoodsVo fGoodsVo:list){
            fGoodsVo.setPics(shoesMapper.getAllPicById(fGoodsVo.getShoesid()));
        }
        return list;
    }
    //筛选（对于当前页是在搜索之后的）
    @Override
    public List<FGoodsVo> filterSearch(String filterList,String value,Integer pageCode,Integer size) {
        Integer start = (pageCode-1) * size;
        value = "'%"+value+"%'";
        filterList = "'"+filterList+"'";
        List<FGoodsVo> list = shoesMapper.getFGoodsVoFilterPvName(value,start,size,filterList);
        for(FGoodsVo fGoodsVo:list){
            fGoodsVo.setPics(shoesMapper.getAllPicById(fGoodsVo.getShoesid()));
        }
        return list;
    }

    @Override
    public Integer getCountFilterPropVal(String filterList, Integer propertyValueId) {
        filterList = "'"+filterList+"'";
        return shoesMapper.getFGoodsVoFilterPvBrandCount(propertyValueId,filterList);
    }

    @Override
    public Integer getCountFilterSearch(String filterList, String value) {
        value = "'%"+value+"%'";
        filterList = "'"+filterList+"'";
        return shoesMapper.getFGoodsVoFilterPvNameCount(value,filterList);
    }
}
