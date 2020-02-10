package com.hdi.hdi.pojo;

import java.util.Date;

public class Interaction {
    private String herbDrugId;

    private String drugId;

    private String herbId;

    private String interactions;

    private String interactionsClass;

    private String rating;

    private Date createTime;

    private Date updateTime;

    public Interaction(String herbDrugId, String drugId, String herbId, String interactions, String interactionsClass, String rating, Date createTime, Date updateTime) {
        this.herbDrugId = herbDrugId;
        this.drugId = drugId;
        this.herbId = herbId;
        this.interactions = interactions;
        this.interactionsClass = interactionsClass;
        this.rating = rating;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Interaction() {
        super();
    }

    public String getHerbDrugId() {
        return herbDrugId;
    }

    public void setHerbDrugId(String herbDrugId) {
        this.herbDrugId = herbDrugId == null ? null : herbDrugId.trim();
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

    public String getInteractions() {
        return interactions;
    }

    public void setInteractions(String interactions) {
        this.interactions = interactions == null ? null : interactions.trim();
    }

    public String getInteractionsClass() {
        return interactionsClass;
    }

    public void setInteractionsClass(String interactionsClass) {
        this.interactionsClass = interactionsClass == null ? null : interactionsClass.trim();
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating == null ? null : rating.trim();
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