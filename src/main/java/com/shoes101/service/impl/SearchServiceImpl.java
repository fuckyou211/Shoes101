package com.shoes101.service.impl;

import com.shoes101.mapper.ShoesMapper;
import com.shoes101.redis.FGoodsKey;
import com.shoes101.redis.RedisService;
import com.shoes101.service.SearchService;
import com.shoes101.vo.FGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ShoesMapper shoesMapper;
    @Autowired
    private RedisService redisService;
    //分页搜索
    @Override
    public List<FGoodsVo> search(String value,Integer pageCode,Integer size) {
        //List<FGoodsVo> list = redisService.get(FGoodsKey.getGoodsListSearch,"value",true,FGoodsVo.class);
        /*if(list==null){
            String value1 = "'%"+value+"%'";
            List<FGoodsVo> list1 = shoesMapper.searchByName(value1);
            redisService.set(FGoodsKey.getGoodsListSearch,value,list1);
            return list1;
        }
        else{
            return list;
        }
    */
        value = "'%"+value+"%'";
        Integer start = pageCode*size;
        List<FGoodsVo> list = shoesMapper.searchByNamePage(value,start,size);
        for(FGoodsVo fGoodsVo:list){
            fGoodsVo.setPics(shoesMapper.getAllPicById(fGoodsVo.getShoesid()));
        }
        return list;
    }

    //不分页
    @Override
    public List<FGoodsVo> search(String value) {
        List<FGoodsVo> list = redisService.get(FGoodsKey.getGoodsListSearch,"value",true,FGoodsVo.class);
        if(list==null){
            String value1 = "'%"+value+"%'";
            List<FGoodsVo> list1 = shoesMapper.searchByName(value1);
            for(FGoodsVo fGoodsVo:list1){
                fGoodsVo.setPics(shoesMapper.getAllPicById(fGoodsVo.getShoesid()));
            }
            redisService.set(FGoodsKey.getGoodsListSearch,value,list1);
            return list1;
        }
        else{
            return list;
        }
    }
    //得到条数
    @Override
    public Integer searchByNameCount(String value) {
        value = "'%"+value+"%'";
        return shoesMapper.getSearchByNameCount(value);
    }
}
