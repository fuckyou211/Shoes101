package com.shoes101.pojo;

import java.io.Serializable;
import java.util.Date;

public class Shoesorder implements Serializable {
    private Integer orderid;

    private String userid;

    private Double totalprice;

    private Double totticketprice;

    private Date buydate;

    private String remark;

    private String contactname;

    private String contactphone;

    private String receiptaddress;

    private Integer state;

    private String cancel;

    private Integer validity;

    private static final long serialVersionUID = 1L;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
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

    public Date getBuydate() {
        return buydate;
    }

    public void setBuydate(Date buydate) {
        this.buydate = buydate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getContactname() {
        return contactname;
    }

    public void setContactname(String contactname) {
        this.contactname = contactname == null ? null : contactname.trim();
    }

    public String getContactphone() {
        return contactphone;
    }

    public void setContactphone(String contactphone) {
        this.contactphone = contactphone == null ? null : contactphone.trim();
    }

    public String getReceiptaddress() {
        return receiptaddress;
    }

    public void setReceiptaddress(String receiptaddress) {
        this.receiptaddress = receiptaddress == null ? null : receiptaddress.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCancel() {
        return cancel;
    }

    public void setCancel(String cancel) {
        this.cancel = cancel == null ? null : cancel.trim();
    }

    public Integer getValidity() {
        return validity;
    }

    public void setValidity(Integer validity) {
        this.validity = validity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", orderid=").append(orderid);
        sb.append(", userid=").append(userid);
        sb.append(", totalprice=").append(totalprice);
        sb.append(", totticketprice=").append(totticketprice);
        sb.append(", buydate=").append(buydate);
        sb.append(", remark=").append(remark);
        sb.append(", contactname=").append(contactname);
        sb.append(", contactphone=").append(contactphone);
        sb.append(", receiptaddress=").append(receiptaddress);
        sb.append(", state=").append(state);
        sb.append(", cancel=").append(cancel);
        sb.append(", validity=").append(validity);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}