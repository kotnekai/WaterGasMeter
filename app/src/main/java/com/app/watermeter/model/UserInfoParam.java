package com.app.watermeter.model;

/**
 * Create by Admin on 2018/8/25
 */
public class UserInfoParam {
    private String contact;
    private String password;
    private String confirmPsw;
    private String email;
    private String realName;

    public UserInfoParam(String contact, String password, String confirmPsw, String email, String realName) {
        this.contact = contact;
        this.password = password;
        this.confirmPsw = confirmPsw;
        this.email = email;
        this.realName = realName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPsw() {
        return confirmPsw;
    }

    public void setConfirmPsw(String confirmPsw) {
        this.confirmPsw = confirmPsw;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
