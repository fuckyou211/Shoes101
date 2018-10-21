package com.shoes101.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "rushbuy")
public class Rushbuy implements Serializable {
    @Id
    @GeneratedValue
    private Integer rushbuyid;

    private Integer shoesid;

    private Date begintime;

    private Date endtime;

    private Integer rbprice;

    private Integer rbamount;

    private Integer limitN;

    private static final long serialVersionUID = 1L;

    public Integer getRushbuyid() {
        return rushbuyid;
    }

    public void setRushbuyid(Integer rushbuyid) {
        this.rushbuyid = rushbuyid;
    }

    public Integer getShoesid() {
        return shoesid;
    }

    public void setShoesid(Integer shoesid) {
        this.shoesid = shoesid;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getRbprice() {
        return rbprice;
    }

    public void setRbprice(Integer rbprice) {
        this.rbprice = rbprice;
    }

    public Integer getRbamount() {
        return rbamount;
    }

    public void setRbamount(Integer rbamount) {
        this.rbamount = rbamount;
    }

    public Integer getLimitN() {
        return limitN;
    }

    public void setLimitN(Integer limitN) {
        this.limitN = limitN;
    }

    public Rushbuy() {
    }
}