package com.shoes101.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ShoesorderVo {

    private Integer orderid;

    private Integer userid;

    private Double totalprice;

    private Double totticketprice;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String buydate;

    private String remark;

    private String contactname;

    private String contactphone;

    private String receiptaddress;

    private Integer state;

    private Integer cancel;

    private Integer validity;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Double getTotticketprice() {
        return totticketprice;
    }

    public void setTotticketprice(Double totticketprice) {
        this.totticketprice = totticketprice;
    }

    public String getBuydate() {
        return buydate;
    }

    public void setBuydate(String buydate) {
        this.buydate = buydate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname;
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone;
    }

    public String getReceiptaddress() {
        return receiptaddress;
    }

    public void setReceiptaddress(String receiptaddress) {
        this.receiptaddress = receiptaddress;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getCancel() {
        return cancel;
    }

    public void setCancel(Integer cancel) {
        this.cancel = cancel;
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }
}
