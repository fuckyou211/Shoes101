package com.shoes101.service;

import java.util.Date;
import java.util.List;

public interface RushMService {

    //获取所有鞋品以供填写抢购信息
    public String getShoesForRush();

    //将第二步信息存入抢购活动表 并获取所有库存返回
    public String getShoesskuForRush(int shoesid, int price, int shoessku, Date starttime, Date endtime);

    //第三步填写完库存 存入rushsku表中
    public String sendInRushsku(List<Integer> skuidlist,int shoesid,
                             List<Integer> quantitylist);

    //抢购管理-查看所有抢购
    public String getAllRush();

    //抢购管理-删除抢购
    public String deleteRush(int rushbuyid);

    //
    public String updateShoesskuForRush(int shoesid, int price, int shoessku, Date starttime, Date endtime,int rushbuyid);

    public String sendInUpdateRushsku(List<Integer> skuidlist,int shoesid,
                                List<Integer> quantitylist,int rushbuyid);

}
