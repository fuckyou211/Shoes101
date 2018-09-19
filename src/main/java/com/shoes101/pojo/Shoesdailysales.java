package com.shoes101.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "shoesdailysales")
public class Shoesdailysales implements Serializable {
    @Id
    @GeneratedValue
    private Integer sdid;

    private String date;

    private Date sales;

    private Integer shoesid;

    private static final long serialVersionUID = 1L;

    public Integer getSdid() {
        return sdid;
    }

    public void setSdid(Integer sdid) {
        this.sdid = sdid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Date getSales() {
        return sales;
    }

    public void setSales(Date sales) {
        this.sales = sales;
    }

    public Integer getShoesid() {
        return shoesid;
    }

    public void setShoesid(Integer shoesid) {
        this.shoesid = shoesid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", sdid=").append(sdid);
        sb.append(", date=").append(date);
        sb.append(", sales=").append(sales);
        sb.append(", shoesid=").append(shoesid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Shoesdailysales() {
    }
}