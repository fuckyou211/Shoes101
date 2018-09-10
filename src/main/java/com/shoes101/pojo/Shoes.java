package com.shoes101.pojo;

import java.io.Serializable;

public class Shoes implements Serializable {
    private Integer shoesid;

    private String shoesname;

    private Integer catalogid;

    private Integer sales;

    private String isdropoff;

    private String label;

    private static final long serialVersionUID = 1L;

    public Integer getShoesid() {
        return shoesid;
    }

    public void setShoesid(Integer shoesid) {
        this.shoesid = shoesid;
    }

    public String getShoesname() {
        return shoesname;
    }

    public void setShoesname(String shoesname) {
        this.shoesname = shoesname == null ? null : shoesname.trim();
    }

    public Integer getCatalogid() {
        return catalogid;
    }

    public void setCatalogid(Integer catalogid) {
        this.catalogid = catalogid;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getIsdropoff() {
        return isdropoff;
    }

    public void setIsdropoff(String isdropoff) {
        this.isdropoff = isdropoff == null ? null : isdropoff.trim();
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label == null ? null : label.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shoesid=").append(shoesid);
        sb.append(", shoesname=").append(shoesname);
        sb.append(", catalogid=").append(catalogid);
        sb.append(", sales=").append(sales);
        sb.append(", isdropoff=").append(isdropoff);
        sb.append(", label=").append(label);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}