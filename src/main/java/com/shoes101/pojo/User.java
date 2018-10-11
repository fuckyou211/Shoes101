package com.shoes101.pojo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.io.Serializable;

@Entity(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue
    private Integer userid;

    @NotNull(message="名字不能为空！")
    private String username;

    @NotNull(message="密码不能为空！")
    private String password;

    @NotNull(message="手机号码不能为空！")
    private String phone;

    @NotNull(message="性别不能为空！")
    private String gender;

    private String headpic;

    private String paypassword;

    private int cold;

    public int getCold() {
        return cold;
    }

    public void setCold(int cold) {
        this.cold = cold;
    }

    private static final long serialVersionUID = 1L;

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
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic == null ? null : headpic.trim();
    }

    public String getPaypassword() {
        return paypassword;
    }

    public void setPaypassword(String paypassword) {
        this.paypassword = paypassword == null ? null : paypassword.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", userid=").append(userid);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", phone=").append(phone);
        sb.append(", gender=").append(gender);
        sb.append(", headpic=").append(headpic);
        sb.append(", paypassword=").append(paypassword);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    public User() {
    }
}