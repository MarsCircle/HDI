package com.hdi.hdi.pojo;

import java.util.Date;

public class HDCompound {
    private String hdAcmpdId;

    private String drugId;

    private String herbId;

    private String acmpdId;

    private String source;

    private Date createTime;

    private Date updateTime;

    public HDCompound(String hdAcmpdId, String drugId, String herbId, String acmpdId, String source, Date createTime, Date updateTime) {
        this.hdAcmpdId = hdAcmpdId;
        this.drugId = drugId;
        this.herbId = herbId;
        this.acmpdId = acmpdId;
        this.source = source;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public HDCompound() {
        super();
    }

    public String getHdAcmpdId() {
        return hdAcmpdId;
    }

    public void setHdAcmpdId(String hdAcmpdId) {
        this.hdAcmpdId = hdAcmpdId == null ? null : hdAcmpdId.trim();
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId == null ? null : drugId.trim();
    }

    public String getHerbId() {
        return herbId;
    }

    public void setHerbId(String herbId) {
        this.herbId = herbId == null ? null : herbId.trim();
    }

    public String getAcmpdId() {
        return acmpdId;
    }

    public void setAcmpdId(String acmpdId) {
        this.acmpdId = acmpdId == null ? null : acmpdId.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
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