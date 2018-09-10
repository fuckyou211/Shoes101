package com.shoes101.pojo;

import java.io.Serializable;
import java.util.Date;

public class Rushbuy implements Serializable {
    private Integer rushbuyid;

    private Integer shoesid;

    private Date begintime;

    private Date endtime;

    private Integer rbprice;

    private Integer rbamount;

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", rushbuyid=").append(rushbuyid);
        sb.append(", shoesid=").append(shoesid);
        sb.append(", begintime=").append(begintime);
        sb.append(", endtime=").append(endtime);
        sb.append(", rbprice=").append(rbprice);
        sb.append(", rbamount=").append(rbamount);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}