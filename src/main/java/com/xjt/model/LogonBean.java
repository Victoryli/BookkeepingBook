package com.xjt.model;

/**
 * @author liqing
 * @version 1.0
 * @date 2019-12-26 22:14
 */
public class LogonBean {


    private String email;
    private String mobile;
    private String username;
    private String password;

    public LogonBean(){

    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
