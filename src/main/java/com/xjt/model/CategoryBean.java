package com.xjt.model;

/**
 * @author liqing
 * @version 1.0
 * @date 2020-01-02 10:26
 */
public class CategoryBean {
    private int id;
    private String name;
    private int userid;

    public CategoryBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
