package com.shoes101.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "shoespic")
public class Shoespic implements Serializable {
    @Id
    @GeneratedValue
    private Integer picid;

    private Integer shoesid;

    private String picaddress;

    private static final long serialVersionUID = 1L;

    public Integer getPicid() {
        return picid;
    }

    public void setPicid(Integer picid) {
        this.picid = picid;
    }

    public Integer getShoesid() {
        return shoesid;
    }

    public void setShoesid(Integer shoesid) {
        this.shoesid = shoesid;
    }

    public String getPicaddress() {
        return picaddress;
    }

    public void setPicaddress(String picaddress) {
        this.picaddress = picaddress == null ? null : picaddress.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", picid=").append(picid);
        sb.append(", shoesid=").append(shoesid);
        sb.append(", picaddress=").append(picaddress);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public Shoespic() {
    }
}