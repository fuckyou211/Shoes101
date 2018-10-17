package com.shoes101.vo;

import com.shoes101.pojo.Propertyvalue;

public class PropertyValueVo implements Comparable{

    private Integer propertyvalueid;

    private Integer propertyid;

    private String propertyvalue;

    public Integer getPropertyvalueid() {
        return propertyvalueid;
    }

    public void setPropertyvalueid(Integer propertyvalueid) {
        this.propertyvalueid = propertyvalueid;
    }

    public Integer getPropertyid() {
        return propertyid;
    }

    public void setPropertyid(Integer propertyid) {
        this.propertyid = propertyid;
    }

    public String getPropertyvalue() {
        return propertyvalue;
    }

    public void setPropertyvalue(String propertyvalue) {
        this.propertyvalue = propertyvalue;
    }


    @Override
    public String toString() {
        return "PropertyValueVo{" +
                "propertyvalueid=" + propertyvalueid +
                ", propertyid=" + propertyid +
                ", propertyvalue='" + propertyvalue + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        PropertyValueVo pv = (PropertyValueVo)o;
        Integer num = pv.propertyvalueid-this.propertyvalueid;
        if(num==0)
            return 0;
        else if(num>0){
            return -1;
        }
        else return 1;
    }
}
