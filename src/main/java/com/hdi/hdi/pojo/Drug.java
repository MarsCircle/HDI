package com.hdi.hdi.pojo;

import java.util.Date;

public class Drug {
    private String drugId;

    private String drugName;

    private String drugIndication;

    private String drugType;

    private String drugClass;

    private String route;

    private String drugbankId;

    private Date createTime;

    private Date updateTime;

    public Drug(String drugId, String drugName, String drugIndication, String drugType, String drugClass, String route, String drugbankId, Date createTime, Date updateTime) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.drugIndication = drugIndication;
        this.drugType = drugType;
        this.drugClass = drugClass;
        this.route = route;
        this.drugbankId = drugbankId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Drug() {
        super();
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId == null ? null : drugId.trim();
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName == null ? null : drugName.trim();
    }

    public String getDrugIndication() {
        return drugIndication;
    }

    public void setDrugIndication(String drugIndication) {
        this.drugIndication = drugIndication == null ? null : drugIndication.trim();
    }

    public String getDrugType() {
        return drugType;
    }

    public void setDrugType(String drugType) {
        this.drugType = drugType == null ? null : drugType.trim();
    }

    public String getDrugClass() {
        return drugClass;
    }

    public void setDrugClass(String drugClass) {
        this.drugClass = drugClass == null ? null : drugClass.trim();
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route == null ? null : route.trim();
    }

    public String getDrugbankId() {
        return drugbankId;
    }

    public void setDrugbankId(String drugbankId) {
        this.drugbankId = drugbankId == null ? null : drugbankId.trim();
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
}