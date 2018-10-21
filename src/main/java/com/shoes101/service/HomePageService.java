package com.shoes101.service;

import com.shoes101.vo.FGoodsVo;

import java.util.List;

public interface HomePageService {

    public List<FGoodsVo> getNewestGoods(Integer count,Integer CatalogId);

}
