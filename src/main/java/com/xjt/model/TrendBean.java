package com.xjt.model;

/**
 * @author liqing
 * @version 1.0
 * @date 2020-01-05 17:29
 */
public class TrendBean {
    private int userid;
    private int month;
    private double spend;
    private String days;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public double getSpend() {
        return spend;
    }

    public void setSpend(double spend) {
        this.spend = spend;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }
}
