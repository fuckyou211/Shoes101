package com.shoes101.pojo;
//<id column="user_id" property="userId" jdbcType="INTEGER" />
//<result column="user_name" property="userName" jdbcType="VARCHAR" />
//<result column="password" property="password" jdbcType="VARCHAR" />
//<result column="phone" property="phone" jdbcType="VARCHAR" />
public class User {

    private String userName;
    private int userId;
    private String password;
    private String phone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }



}
