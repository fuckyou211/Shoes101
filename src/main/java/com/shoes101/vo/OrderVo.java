package com.shoes101.vo;

import java.util.List;

public class OrderVo {

    private int userid;
    private List<SkuIdAndQuantityVo> skuidandqty;
    private String contactPhone;
    private String contactName;
    private String remark;
    private String receiptaddress;

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
}
