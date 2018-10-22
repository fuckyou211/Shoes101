package com.shoes101.service.impl;

import com.shoes101.mapper.ShoesMapper;
import com.shoes101.redis.FGoodsKey;
import com.shoes101.redis.RedisService;
import com.shoes101.service.HomePageService;
import com.shoes101.service.ShoesHeaderService;
import com.shoes101.util.DateUtils;
import com.shoes101.vo.FGoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private ShoesMapper shoesMapper;
    @Autowired
    private RedisService redisService;
    @Autowired
    private ShoesHeaderService shoesHeaderService;
    public List<FGoodsVo> getNewestGoods(Integer count,Integer catalogId){
      //取缓存
      List<FGoodsVo> list  = redisService.get(FGoodsKey.getGoodsListCatalog,""+catalogId,true,FGoodsVo.class);
      if(list==null ){
         list = shoesHeaderService.listUnderCatalog(catalogId);
          for(FGoodsVo fGoodsVo:list){
              fGoodsVo.setPics(shoesMapper.getAllPicById(fGoodsVo.getShoesid()));
          }
      }
      ListSort(list);
      //System.out.println(list);
      if(list.size()>=count)
        return list.subList(0,count);
      else return list;
    }

    @Override
    public List<FGoodsVo> getOldestGoods(Integer count) {
        List<FGoodsVo> list = shoesMapper.getOldestGoods(count);
        for(FGoodsVo fGoodsVo:list){
            fGoodsVo.setPics(shoesMapper.getAllPicById(fGoodsVo.getShoesid()));
        }
        return list;
    }

    @Override
    public List<FGoodsVo> getHotSale(Integer count) {
        Date date = new Date();
        String dateString = DateUtils.transferDateToString_YM(date);
        dateString = "'%"+dateString+"%'";
        List<FGoodsVo> list = shoesMapper.getHotSale(count,dateString);
        for(FGoodsVo fGoodsVo:list){
            fGoodsVo.setPics(shoesMapper.getAllPicById(fGoodsVo.getShoesid()));
        }
        return list;
    }

    //排序List<FGoodsVo> list ,按日期
    private static void ListSort(List<FGoodsVo> list) {
        Collections.sort(list, new Comparator<FGoodsVo>() {
            @Override
            public int compare(FGoodsVo o1, FGoodsVo o2) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date dt1 = format.parse(o1.getAdddate());
                    Date dt2 = format.parse(o2.getAdddate());
                    if (dt1.getTime() > dt2.getTime()) {
                        return -1;
                    } else if (dt1.getTime() < dt2.getTime()) {
                        return 1;
                    } else {
                        return 0;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return 0;
            }
        });
    }
}
