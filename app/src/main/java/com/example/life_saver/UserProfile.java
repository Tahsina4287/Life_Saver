package com.example.life_saver;

public class UserProfile {
    private String mail;
    private String phone;
    private String pass;
    private String bg;
    private String area;
    private String dob;

    public UserProfile(String mail, String phone, String pass, String bg, String area, String dob) {
        this.mail = mail;
        this.phone = phone;
        this.pass = pass;
        this.bg = bg;
        this.area = area;
        this.dob = dob;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }
}
