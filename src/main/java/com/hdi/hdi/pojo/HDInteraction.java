package com.hdi.hdi.pojo;

import java.util.Date;

public class HDInteraction {
    private String herbDrugId;

    private String drugId;

    private String herbId;

    private String interactionsDescribe;

    private String adverseEffect;

    private String evidences;

    private String level;

    private String source;



    public String getHerbName() {
        return herbName;
    }

    public void setHerbName(String herbName) {
        this.herbName = herbName;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    private String herbName;

    private String drugName;

    public HDInteraction(String herbDrugId, String drugId, String herbId, String interactionsDescribe, String adverseEffect, String evidences, String level, String source, Date createTime, Date updateTime) {
        this.herbDrugId = herbDrugId;
        this.drugId = drugId;
        this.herbId = herbId;
        this.interactionsDescribe = interactionsDescribe;
        this.adverseEffect = adverseEffect;
        this.evidences = evidences;
        this.level = level;
        this.source = source;

    }

    public HDInteraction() {
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

    public String getInteractionsDescribe() {
        return interactionsDescribe;
    }

    public void setInteractionsDescribe(String interactionsDescribe) {
        this.interactionsDescribe = interactionsDescribe == null ? null : interactionsDescribe.trim();
    }

    public String getAdverseEffect() {
        return adverseEffect;
    }

    public void setAdverseEffect(String adverseEffect) {
        this.adverseEffect = adverseEffect == null ? null : adverseEffect.trim();
    }

    public String getEvidences() {
        return evidences;
    }

    public void setEvidences(String evidences) {
        this.evidences = evidences == null ? null : evidences.trim();
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level == null ? null : level.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

}