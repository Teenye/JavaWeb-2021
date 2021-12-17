package com.yiyue.pojo;

import java.util.Date;

public class Cart {
    private Integer userid;
    private Integer goodid;
    private Integer num;
    private String date;

    @Override
    public String toString() {
        return "Cart{" +
                "userid=" + userid +
                ", goodid=" + goodid +
                ", num=" + num +
                ", date='" + date + '\'' +
                '}';
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getGoodid() {
        return goodid;
    }

    public void setGoodid(Integer goodid) {
        this.goodid = goodid;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
