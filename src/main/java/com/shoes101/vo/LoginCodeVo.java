package com.shoes101.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class LoginCodeVo {

    @NotNull
    private String mobile;

    @NotNull
    @Length(min=6,max = 6)
    private String code;

    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "LoginCodeVo{" +
                "mobile='" + mobile + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
