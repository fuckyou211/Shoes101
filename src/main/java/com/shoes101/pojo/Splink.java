package com.shoes101.pojo;

import java.io.Serializable;

public class Splink implements Serializable {
    private Integer spid;

    private Integer shoesid;

    private Integer propertyid;

    private Integer propertyvalueid;

    private static final long serialVersionUID = 1L;

    public Integer getSpid() {
        return spid;
    }

    public void setSpid(Integer spid) {
        this.spid = spid;
    }

    public Integer getShoesid() {
        return shoesid;
    }

    public void setShoesid(Integer shoesid) {
        this.shoesid = shoesid;
    }

    public Integer getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(Integer propertyid) {
        this.propertyid = propertyid;
    }

    public Integer getPropertyvalueid() {
        return propertyvalueid;
    }

    public void setPropertyvalueid(Integer propertyvalueid) {
        this.propertyvalueid = propertyvalueid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", spid=").append(spid);
        sb.append(", shoesid=").append(shoesid);
        sb.append(", propertyid=").append(propertyid);
        sb.append(", propertyvalueid=").append(propertyvalueid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}