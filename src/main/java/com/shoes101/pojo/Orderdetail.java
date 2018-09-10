package com.shoes101.pojo;

import java.io.Serializable;

public class Orderdetail implements Serializable {
    private Integer detailid;

    private Integer orderid;

    private Integer skuid;

    private Integer quantity;

    private Double price;

    private Double ticketprice;

    private static final long serialVersionUID = 1L;

    public Integer getDetailid() {
        return detailid;
    }

    public void setDetailid(Integer detailid) {
        this.detailid = detailid;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
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
        sb.append(", detailid=").append(detailid);
        sb.append(", orderid=").append(orderid);
        sb.append(", skuid=").append(skuid);
        sb.append(", quantity=").append(quantity);
        sb.append(", price=").append(price);
        sb.append(", ticketprice=").append(ticketprice);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}