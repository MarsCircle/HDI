package com.hdi.hdi.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;


@JsonIgnoreProperties(value = {"password", "userId"})
public class User {
    public User(Long userId, String username, String password, String email, String occupation, String nameChinese, String phone, String address, String company, Integer role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.occupation = occupation;
        this.nameChinese = nameChinese;
        this.phone = phone;
        this.address = address;
        this.company = company;
        this.role = role;
    }

    private Long userId;

    private String username;

    private String password;

    private String email;

    private String occupation;

    private String nameChinese;

    private String phone;

    private String address;

    private String company;

    private Integer role;


    private byte[] workPermit;

    public User( Long userId,String username, String password, String email, String occupation, String nameChinese, String phone, String address, String company, Integer role, Date createTime, Date updateTime) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.occupation = occupation;
        this.nameChinese = nameChinese;
        this.phone = phone;
        this.address = address;
        this.company = company;
        this.role = role;

        this.workPermit = null;
    }


    public User( String username, String password, String email, String occupation, String nameChinese, String phone, String address, String company, Integer role, Date createTime, Date updateTime, byte[] workPermit) {

        this.username = username;
        this.password = password;
        this.email = email;
        this.occupation = occupation;
        this.nameChinese = nameChinese;
        this.phone = phone;
        this.address = address;
        this.company = company;
        this.role = role;
        this.workPermit = workPermit;
    }
    public User() {
        super();
    }

    public User(String username, String password, String email, String phone, String occupation, String nameChinese, String address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.occupation = occupation;
        this.nameChinese = nameChinese;
        this.phone = phone;
        this.address = address;
        this.company = "";
        this.workPermit= new byte[]{ };
        this.role = 0;
    }

    public User(String username, String password, String email, String phone, String occupation, String nameChinese, String address, String company, byte[] workPermit) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.occupation = occupation;
        this.nameChinese = nameChinese;
        this.phone = phone;
        this.address = address;
        this.company = company;
        this.role = 1;
        this.workPermit = workPermit;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation == null ? null : occupation.trim();
    }

    public String getNameChinese() {
        return nameChinese;
    }

    public void setNameChinese(String nameChinese) {
        this.nameChinese = nameChinese == null ? null : nameChinese.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public byte[] getWorkPermit() {
        return workPermit;
    }

    public void setWorkPermit(byte[] workPermit) {
        this.workPermit = workPermit;
    }
}