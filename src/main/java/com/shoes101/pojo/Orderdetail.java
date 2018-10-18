package com.shoes101.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "orderdetail")
public class Orderdetail implements Serializable {
    @Id
    @GeneratedValue
    private Integer detailid;

    private Integer orderid;

    private Integer skuid;

    private Integer quantity;

    private Double price;

    private Double ticketprice;

    private String shoesname;

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

    public String getShoesname() {
        return shoesname;
    }

    public void setShoesname(String shoesname) {
        this.shoesname = shoesname;
    }

    @Override
    public String toString() {
        return "Orderdetail{" +
                "detailid=" + detailid +
                ", orderid=" + orderid +
                ", skuid=" + skuid +
                ", quantity=" + quantity +
                ", price=" + price +
                ", ticketprice=" + ticketprice +
                ", shoesname='" + shoesname + '\'' +
                '}';
    }

    public Orderdetail() {
    }
}