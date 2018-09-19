package com.shoes101.pojo;

import java.io.Serializable;

public class Spfilter implements Serializable {
    private Integer shoesid;

    private String propertyvalueid;

    private static final long serialVersionUID = 1L;

    public Spfilter() {
    }

    public Integer getShoesid() {
        return shoesid;
    }

    public void setShoesid(Integer shoesid) {
        this.shoesid = shoesid;
    }

    public String getPropertyvalueid() {
        return propertyvalueid;
    }

    public void setPropertyvalueid(String propertyvalueid) {
        this.propertyvalueid = propertyvalueid;
    }

    @Override
    public String toString() {
        return "Spfilter{" +
                "shoesid=" + shoesid +
                ", propertyvalueid='" + propertyvalueid + '\'' +
                '}';
    }
}