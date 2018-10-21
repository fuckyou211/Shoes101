package com.shoes101.service;

import com.shoes101.vo.DaySellVo;
import com.shoes101.vo.SellsAVo;

import java.util.List;

public interface CellsService {

    //默认返回
    public String tocells();

    //根据年月查询月销量
    public List<DaySellVo> getYmsells(String year,String month);

}
