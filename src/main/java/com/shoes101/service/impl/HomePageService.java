package com.shoes101.service.impl;

import com.shoes101.mapper.ShoesMapper;
import com.shoes101.vo.FGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional

public class HomePageService {

    @Autowired
    ShoesMapper shoesMapper;
    public List<FGoodsVo> getNewestGoods(Integer count){
      List<FGoodsVo> list =  shoesMapper.getFGoodsVoOrderByDate(count);
      System.out.println(list);
        for(int i = 0;i < list.size();i++)
        {
            list.get(i).setPics(shoesMapper.getAllPicById(list.get(i).getShoesid()));
        }
        return list;
    }
}
