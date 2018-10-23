package com.shoes101.vo;

import java.util.List;

public class RushOrderVo {
    private int shoesid;

    private int rushbuyid;

    private int shoessku;

    private int quantity;

    private int userid;
    private List<SkuIdAndQuantityVo> skuidandqty;
    private String contactPhone;
    private String contactName;
    private String remark;
    private String receiptaddress;
    private String token;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public List<SkuIdAndQuantityVo> getSkuidandqty() {
        return skuidandqty;
    }

    public void setSkuidandqty(List<SkuIdAndQuantityVo> skuidandqty) {
        this.skuidandqty = skuidandqty;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getReceiptaddress() {
        return receiptaddress;
    }

    public void setReceiptaddress(String receiptaddress) {
        this.receiptaddress = receiptaddress;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public RushOrderVo() {
    }

    public int getRushbuyid() {
        return rushbuyid;
    }

    public void setRushbuyid(int rushbuyid) {
        this.rushbuyid = rushbuyid;
    }

    public int getShoesid() {
        return shoesid;
    }

    public void setShoesid(int shoesid) {
        this.shoesid = shoesid;
    }

    public int getShoessku() {
        return shoessku;
    }

    public void setShoessku(int shoessku) {
        this.shoessku = shoessku;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
