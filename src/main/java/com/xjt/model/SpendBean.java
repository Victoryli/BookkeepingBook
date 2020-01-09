package com.xjt.model;

/**
 * @author liqing
 * @version 1.0
 * @date 2020-01-03 22:09
 */
public class SpendBean {

    private int id;
    private double spend;
    private String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
