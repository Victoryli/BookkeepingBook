package com.xjt.model;



/**
 * @author liqing
 * @version 1.0
 * @date 2019-12-25 14:37
 */
public class User {
    private int id;
    private String username;
    private String email;
    private String mobile;
    private String password;

    public User(String username,String email,String mobile,String password){
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.password = password;

    }

    public User(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getMobile(){
        return mobile;
    }
    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }




}
