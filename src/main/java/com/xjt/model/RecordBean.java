package com.xjt.model;

import java.util.Date;

/**
 * @author liqing
 * @version 1.0
 * @date 2020-01-03 15:51
 */
public class RecordBean {
    private int id;
    private double spend;
    private  String category;
    private int userid;
    private  String comment;
    private Date date;

    public RecordBean(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public double getSpend() {
        return spend;
    }

    public void setSpend(double spend) {
        this.spend = spend;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }




    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
