package com.shoes101.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
//管理员
//@Table(name="admin")

public class Admin {

    @Id
    @GeneratedValue
    private int adminid;
    private String adminname;
    private String password;

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getAdminName() {
        return adminname;
    }

    public void setAdminName(String adminName) {
        this.adminname = adminname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
