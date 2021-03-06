package com.shoes101.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Entity(name = "shoesorder")
public class Shoesorder implements Serializable {
    @Id
    @GeneratedValue
    private Integer orderid;

    private Integer userid;

    private Double totalprice;

    private Double totticketprice;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date buydate;

    private String remark;

    private String contactname;

    private String contactphone;

    private String receiptaddress;

    private Integer state;

    private Integer cancel;

    private Integer validity;

    private static final long serialVersionUID = 1L;

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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getBuydate() {
        return buydate;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
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

    public Shoesorder() {
    }
}