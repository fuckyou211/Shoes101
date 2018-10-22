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
    @Override
    public List<FGoodsVo> search(String value,Integer start,Integer size) {
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
        return shoesMapper.searchByNamePage(value,start,size);
    }
    @Override
    public List<FGoodsVo> search(String value) {
        List<FGoodsVo> list = redisService.get(FGoodsKey.getGoodsListSearch,"value",true,FGoodsVo.class);
        if(list==null){
            String value1 = "'%"+value+"%'";
            List<FGoodsVo> list1 = shoesMapper.searchByName(value1);
            redisService.set(FGoodsKey.getGoodsListSearch,value,list1);
            return list1;
        }
        else{
            return list;
        }
    }
    @Override
    public Integer searchByNameCount(String value) {
        value = "'%"+value+"%'";
        return shoesMapper.getSearchByNameCount(value);
    }
}
