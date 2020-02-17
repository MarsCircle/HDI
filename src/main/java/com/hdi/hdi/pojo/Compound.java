package com.hdi.hdi.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Compound {
    private String acmpdId;

    private String moleculeName;

    private BigDecimal obScore;

    private String canonicalSmiles;

    private String alias;

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

    private Integer pubchemId;

    private String casId;

    private BigDecimal moleculeWeight;

    private Date createTime;

    private Date updateTime;

    public Compound(String acmpdId, String moleculeName, BigDecimal obScore, String canonicalSmiles, String alias, Integer pubchemId, String casId, BigDecimal moleculeWeight, Date createTime, Date updateTime) {
        this.acmpdId = acmpdId;
        this.moleculeName = moleculeName;
        this.obScore = obScore;
        this.canonicalSmiles = canonicalSmiles;
        this.alias = alias;
        this.pubchemId = pubchemId;
        this.casId = casId;
        this.moleculeWeight = moleculeWeight;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Compound() {
        super();
    }

    public String getAcmpdId() {
        return acmpdId;
    }

    public void setAcmpdId(String acmpdId) {
        this.acmpdId = acmpdId == null ? null : acmpdId.trim();
    }

    public String getMoleculeName() {
        return moleculeName;
    }

    public void setMoleculeName(String moleculeName) {
        this.moleculeName = moleculeName == null ? null : moleculeName.trim();
    }

    public BigDecimal getObScore() {
        return obScore;
    }

    public void setObScore(BigDecimal obScore) {
        this.obScore = obScore;
    }

    public String getCanonicalSmiles() {
        return canonicalSmiles;
    }

    public void setCanonicalSmiles(String canonicalSmiles) {
        this.canonicalSmiles = canonicalSmiles == null ? null : canonicalSmiles.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public Integer getPubchemId() {
        return pubchemId;
    }

    public void setPubchemId(Integer pubchemId) {
        this.pubchemId = pubchemId;
    }

    public String getCasId() {
        return casId;
    }

    public void setCasId(String casId) {
        this.casId = casId == null ? null : casId.trim();
    }

    public BigDecimal getMoleculeWeight() {
        return moleculeWeight;
    }

    public void setMoleculeWeight(BigDecimal moleculeWeight) {
        this.moleculeWeight = moleculeWeight;
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