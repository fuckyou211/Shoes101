package com.shoes101.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name ="spfilter" )
public class Spfilter implements Serializable {

    @Id
    @GeneratedValue
    private Integer spfilterid;

    private Integer shoesid;

    private String propertyvalueid;

    private static final long serialVersionUID = 1L;

    public Spfilter() {
    }

    public Integer getSpfilterid() {
        return spfilterid;
    }

    public void setSpfilterid(Integer spfilterid) {
        this.spfilterid = spfilterid;
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