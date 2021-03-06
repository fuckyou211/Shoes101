package com.shoes101.service;

import com.shoes101.vo.FGoodsVo;

import java.util.List;

public interface SearchService {

    public List<FGoodsVo> search(String value,Integer pageCode,Integer size);

    public Integer searchByNameCount(String value);

    public List<FGoodsVo> search(String value);

}
