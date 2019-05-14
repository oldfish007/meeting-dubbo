package com.stylefeng.guns.api.user;

import java.io.Serializable;

/**
 * @author
 * @description 可以供我们API正常访问
 * @date 2019/5/14
 */
public class UserModel implements Serializable {

    private String username;
    private String password;
    private String phone;
    private String email;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
