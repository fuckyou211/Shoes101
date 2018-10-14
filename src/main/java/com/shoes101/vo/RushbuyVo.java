package com.shoes101.vo;

import com.fasterxml.jackson.annotation.JsonFormat;



/**
 * 后台抢购管理Vo
 */
public class RushbuyVo {

    private int rushbuyid;
    private int shoesid;
    private String shoesname;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String begintime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String endtime;
    private int rbprice;
    private int rbamount;
    private String status;
    private String picaddress;

    public String getPicaddress() {
        return picaddress;
    }

    public void setPicaddress(String picaddress) {
        this.picaddress = picaddress;
    }

    public int getRushbuyid() {
        return rushbuyid;
    }

    public void setRushbuyid(int rushbuyid) {
        this.rushbuyid = rushbuyid;
    }

    public int getShoesid() {
        return shoesid;
    }

    public void setShoesid(int shoesid) {
        this.shoesid = shoesid;
    }

    public String getShoesname() {
        return shoesname;
    }

    public void setShoesname(String shoesname) {
        this.shoesname = shoesname;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public int getRbprice() {
        return rbprice;
    }

    public void setRbprice(int rbprice) {
        this.rbprice = rbprice;
    }

    public int getRbamount() {
        return rbamount;
    }

    public void setRbamount(int rbamount) {
        this.rbamount = rbamount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
