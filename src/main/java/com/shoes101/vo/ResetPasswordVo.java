package com.shoes101.vo;

import javax.validation.constraints.NotNull;

public class ResetPasswordVo {

    @NotNull(message="密码不能为空！")
    private String password;

    @NotNull(message="手机号码不能为空！")
    private String mobile;

    @NotNull(message="验证码不能为空！")
    private String code;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

    public ResetPasswordVo() {
    }
}

