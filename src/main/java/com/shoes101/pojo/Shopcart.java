package com.shoes101.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "shopcart")
public class Shopcart implements Serializable {
    @Id
    @GeneratedValue
    private Integer scid;

    private Integer userid;

    private Double sctotalprice;

    private Double scticketprice;

    private Date adddate;

    private static final long serialVersionUID = 1L;

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Double getSctotalprice() {
        return sctotalprice;
    }

    public void setSctotalprice(Double sctotalprice) {
        this.sctotalprice = sctotalprice;
    }

    public Double getScticketprice() {
        return scticketprice;
    }

    public void setScticketprice(Double scticketprice) {
        this.scticketprice = scticketprice;
    }

    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date adddate) {
        this.adddate = adddate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", scid=").append(scid);
        sb.append(", userid=").append(userid);
        sb.append(", sctotalprice=").append(sctotalprice);
        sb.append(", scticketprice=").append(scticketprice);
        sb.append(", adddate=").append(adddate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Shopcart() {
    }
}