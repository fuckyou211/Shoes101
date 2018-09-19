package com.shoes101.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
//@Table(name="propertyvalue")
@Entity(name = "propertyvalue")
public class Propertyvalue implements Serializable {
    @Id
    @GeneratedValue
    private Integer propertyvalueid;

    private Integer propertyid;

    private String propertyvalue;

    private static final long serialVersionUID = 1L;

    public Integer getPropertyvalueid() {
        return propertyvalueid;
    }

    public void setPropertyvalueid(Integer propertyvalueid) {
        this.propertyvalueid = propertyvalueid;
    }

    public Integer getPropertyId() {
        return propertyid;
    }

    public void setPropertyId(Integer propertyid) {
        this.propertyid = propertyid;
    }

    public String getPropertyvalue() {
        return propertyvalue;
    }

    public void setPropertyvalue(String propertyvalue) {
        this.propertyvalue = propertyvalue == null ? null : propertyvalue.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", propertyvalueid=").append(propertyvalueid);
        sb.append(", propertyid=").append(propertyid);
        sb.append(", propertyvalue=").append(propertyvalue);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Propertyvalue() {
    }
}