package com.shoes101.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "rushsku")
public class Rushsku {
    @Id
    @GeneratedValue
    private Integer rushskuid;
    private Integer shoesid;
    private Integer skuid;
    private Integer quantity;

    public Rushsku() {
    }

    public Integer getRushskuid() {
        return rushskuid;
    }

    public void setRushskuid(Integer rushskuid) {
        this.rushskuid = rushskuid;
    }

    public Integer getShoesid() {
        return shoesid;
    }

    public void setShoesid(Integer shoesid) {
        this.shoesid = shoesid;
    }

    public Integer getSkuid() {
        return skuid;
    }

    public void setSkuid(Integer skuid) {
        this.skuid = skuid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Rushsku{" +
                "rushskuid=" + rushskuid +
                ", shoesid=" + shoesid +
                ", skuid=" + skuid +
                ", quantity=" + quantity +
                '}';
    }
}

