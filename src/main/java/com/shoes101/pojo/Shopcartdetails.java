package com.shoes101.pojo;

import java.io.Serializable;

public class Shopcartdetails implements Serializable {
    private Integer scdid;

    private Integer scid;

    private Integer skuid;

    private Integer quantity;

    private Double price;

    private Double ticketprice;

    private static final long serialVersionUID = 1L;

    public Integer getScdid() {
        return scdid;
    }

    public void setScdid(Integer scdid) {
        this.scdid = scdid;
    }

    public Integer getScid() {
        return scid;
    }

    public void setScid(Integer scid) {
        this.scid = scid;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", scdid=").append(scdid);
        sb.append(", scid=").append(scid);
        sb.append(", skuid=").append(skuid);
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append(", ticketprice=").append(ticketprice);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}