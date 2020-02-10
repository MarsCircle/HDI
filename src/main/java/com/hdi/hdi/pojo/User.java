package com.hdi.hdi.pojo;

import java.util.Date;

public class User {
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

    private Byte isStatus;

    private Date createTime;

    private Date updateTime;

    private byte[] workPermit;

    public User(Long userId, String username, String password, String email, String occupation, String nameChinese, String phone, String address, String company, Integer role, Byte isStatus, Date createTime, Date updateTime, byte[] workPermit) {
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
        this.isStatus = isStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.workPermit = workPermit;
    }

    public User() {
        super();
    }

    public User(String username, String password, String email, String phone) {
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

    public Byte getIsStatus() {
        return isStatus;
    }

    public void setIsStatus(Byte isStatus) {
        this.isStatus = isStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public byte[] getWorkPermit() {
        return workPermit;
    }

    public void setWorkPermit(byte[] workPermit) {
        this.workPermit = workPermit;
    }
}