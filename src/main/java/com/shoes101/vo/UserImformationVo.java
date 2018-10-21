package com.shoes101.vo;

import javax.validation.constraints.NotNull;

public class UserImformationVo {

    private Integer userid;
    @NotNull
    private String username;
    @NotNull
    private String phone;
    @NotNull
    private String address;

    public UserImformationVo() {
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserImformationVo{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
