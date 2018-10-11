package com.shoes101.vo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SMSCodeVo {
    @Value("${aliyun.accessKeyId}")
    private String accessKeyId;
    @Value("${aliyun.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aliyun.defaultConnectTimeout}")
    private String defaultConnectTimeout;
    @Value("${aliyun.defaultReadTimeout}")
    private String defaultReadTimeout;
    @Value("${aliyun.SignName}")
    private String SignName;
    @Value("${aliyun.loginCode}")
    private String loginCode;
    @Value("${aliyun.resetPassword}")
    private String resetPassword;
    @Value("${aliyun.payCode}")
    private String payCode;
    @Value("${aliyun.userRegistration}")
    private String userRegistration;

    public SMSCodeVo() {
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getDefaultConnectTimeout() {
        return defaultConnectTimeout;
    }

    public void setDefaultConnectTimeout(String defaultConnectTimeout) {
        this.defaultConnectTimeout = defaultConnectTimeout;
    }

    public String getDefaultReadTimeout() {
        return defaultReadTimeout;
    }

    public void setDefaultReadTimeout(String defaultReadTimeout) {
        this.defaultReadTimeout = defaultReadTimeout;
    }

    public String getSignName() {
        return SignName;
    }

    public void setSignName(String signName) {
        SignName = signName;
    }

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public String getResetPassword() {
        return resetPassword;
    }

    public void setResetPassword(String resetPassword) {
        this.resetPassword = resetPassword;
    }

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getUserRegistration() {
        return userRegistration;
    }

    public void setUserRegistration(String userRegistration) {
        this.userRegistration = userRegistration;
    }
}