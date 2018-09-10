package com.shoes101.pojo;

import java.io.Serializable;

public class Shoessku implements Serializable {
    private Integer skuid;

    private Integer shoesid;

    private String skuproperty;

    private Double price;

    private Double ticketprice;

    private Integer quantity;

    private Integer skusales;

    private static final long serialVersionUID = 1L;

    public Integer getSkuid() {
        return skuid;
    }

    public void setSkuid(Integer skuid) {
        this.skuid = skuid;
    }

    public Integer getShoesid() {
        return shoesid;
    }

    public void setShoesid(Integer shoesid) {
        this.shoesid = shoesid;
    }

    public String getSkuproperty() {
        return skuproperty;
    }

    public void setSkuproperty(String skuproperty) {
        this.skuproperty = skuproperty == null ? null : skuproperty.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTicketprice() {
        return ticketprice;
    }

    public void setTicketprice(Double ticketprice) {
        this.ticketprice = ticketprice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSkusales() {
        return skusales;
    }

    public void setSkusales(Integer skusales) {
        this.skusales = skusales;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", skuid=").append(skuid);
        sb.append(", shoesid=").append(shoesid);
        sb.append(", skuproperty=").append(skuproperty);
        sb.append(", price=").append(price);
        sb.append(", ticketprice=").append(ticketprice);
        sb.append(", quantity=").append(quantity);
        sb.append(", skusales=").append(skusales);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}