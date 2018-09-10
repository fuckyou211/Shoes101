package com.shoes101.pojo;

import java.io.Serializable;

public class Spfilter implements Serializable {
    private Integer shoesid;

    private Integer propertyvalueid;

    private static final long serialVersionUID = 1L;

    public Integer getShoesid() {
        return shoesid;
    }

    public void setShoesid(Integer shoesid) {
        this.shoesid = shoesid;
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
        sb.append(", shoesid=").append(shoesid);
        sb.append(", propertyvalueid=").append(propertyvalueid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}