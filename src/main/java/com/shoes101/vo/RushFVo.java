package com.shoes101.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RushFVo {

    private int rushbuyid;
    private int shoesid;
    private String shoesname;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String begintime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private String endtime;
    //10.21更新 增加限购
    private Integer limitN;
    private int rbprice;
    private int rbamount;
    private String status;
    private String picaddress;


}
