package com.shoes101.vo;

import java.util.List;

/**
 *  销量管理A类
 */
public class SellsAVo {


    //获取当时年月
    private String yearmonth;

    //获取年月交易额
    private int monthmoney;

    //获取年月订单数量
    private int monthorder;

    //获取年月用户的注册数
    private int monthregistuser;

    //交易额比例
    private int moneyup;

    //订单额比例
    private int orderup;

    //用户注册额比例
    private int userup;

    public int getMoneyup() {
        return moneyup;
    }

    public void setMoneyup(int moneyup) {
        this.moneyup = moneyup;
    }

    public int getOrderup() {
        return orderup;
    }

    public void setOrderup(int orderup) {
        this.orderup = orderup;
    }

    public int getUserup() {
        return userup;
    }

    public void setUserup(int userup) {
        this.userup = userup;
    }

    public String getYearmonth() {
        return yearmonth;
    }

    public void setYearmonth(String yearmonth) {
        this.yearmonth = yearmonth;
    }

    public int getMonthmoney() {
        return monthmoney;
    }

    public void setMonthmoney(int monthmoney) {
        this.monthmoney = monthmoney;
    }

    public int getMonthorder() {
        return monthorder;
    }

    public void setMonthorder(int monthorder) {
        this.monthorder = monthorder;
    }

    public int getMonthregistuser() {
        return monthregistuser;
    }

    public void setMonthregistuser(int monthregistuser) {
        this.monthregistuser = monthregistuser;
    }

    //    public List<Integer> getMonthmoney() {
//        return monthmoney;
//    }
//
//    public void setMonthmoney(List<Integer> monthmoney) {
//        this.monthmoney = monthmoney;
//    }
}
