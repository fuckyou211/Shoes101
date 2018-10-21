package com.shoes101.service;


public interface CellsService {

    //默认返回
    public String tocells();

    //根据年月查询月销量
    public String getYmsells(String year,String month);

    //根据年查询年销量 传年份
    public String getYsells(String year);

    //查询每年销量
    public String getEveryYsells();
}
