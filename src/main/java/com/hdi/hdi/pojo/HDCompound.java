package com.hdi.hdi.pojo;

import java.util.Date;

public class HDCompound {
    private String hdAcmpdId;

    private String herbDrugId;


    private String acmpdId;




    public HDCompound(String hdAcmpdId, String herbDrugId, String acmpdId, String source, Date createTime, Date updateTime) {
        this.hdAcmpdId = hdAcmpdId;
        this.herbDrugId = herbDrugId;

        this.acmpdId = acmpdId;


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

    public String getherbDrugId() {
        return herbDrugId;
    }

    public void setherbDrugId(String drugId) {
        this.herbDrugId = drugId == null ? null : drugId.trim();
    }



    public String getAcmpdId() {
        return acmpdId;
    }

    public void setAcmpdId(String acmpdId) {
        this.acmpdId = acmpdId == null ? null : acmpdId.trim();
    }




}