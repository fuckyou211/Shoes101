package com.shoes101.pojo;

import java.util.List;

public class Addshoes {
    private Integer shoesid;

    private String shoesname;

    private Integer catalogid;

    private String label;

    private Integer sales;

    private String isdropoff;

    private String adddate;

    private String shoesdetails;

    private List<Integer> property;

    private List<Integer> propertyvalue;

    private List<Integer> shoescolor;

    private List<Integer> shoessize;

    private Double price;

    private Double ticketprice;

    private Integer quantity;

    private Integer skusales;

    public Addshoes() {
    }

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
        this.shoesname = shoesname;
    }

    public Integer getCatalogid() {
        return catalogid;
    }

    public void setCatalogid(Integer catalogid) {
        this.catalogid = catalogid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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
        this.isdropoff = isdropoff;
    }

    public String getAdddate() {
        return adddate;
    }

    public void setAdddate(String adddate) {
        this.adddate = adddate;
    }

    public String getShoesdetails() {
        return shoesdetails;
    }

    public void setShoesdetails(String shoesdetails) {
        this.shoesdetails = shoesdetails;
    }

    public List<Integer> getProperty() {
        return property;
    }

    public void setProperty(List<Integer> property) {
        this.property = property;
    }

    public List<Integer> getPropertyvalue() {
        return propertyvalue;
    }

    public void setPropertyvalue(List<Integer> propertyvalue) {
        this.propertyvalue = propertyvalue;
    }

    public List<Integer> getShoescolor() {
        return shoescolor;
    }

    public void setShoescolor(List<Integer> shoescolor) {
        this.shoescolor = shoescolor;
    }

    public List<Integer> getShoessize() {
        return shoessize;
    }

    public void setShoessize(List<Integer> shoessize) {
        this.shoessize = shoessize;
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
        return "Addshoes{" +
                "shoesid=" + shoesid +
                ", shoesname='" + shoesname + '\'' +
                ", catalogid=" + catalogid +
                ", label='" + label + '\'' +
                ", sales=" + sales +
                ", isdropoff='" + isdropoff + '\'' +
                ", adddate='" + adddate + '\'' +
                ", shoesdetails='" + shoesdetails + '\'' +
                ", property=" + property +
                ", propertyvalue=" + propertyvalue +
                ", shoescolor=" + shoescolor +
                ", shoessize=" + shoessize +
                ", price=" + price +
                ", ticketprice=" + ticketprice +
                ", quantity=" + quantity +
                ", skusales=" + skusales +
                '}';
    }
}
