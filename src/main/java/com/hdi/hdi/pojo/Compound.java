package com.hdi.hdi.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Compound {
    private String acmpdId;

    private String moleculeName;

    private String obScore;

    private String canonicalSmiles;

    private String alias;

    private String herbName;

    private String drugName;

    private String pubchemId;

    private String casId;

    private String moleculeWeight;


    private String drugbankId;




    public Compound(String acmpdId, String moleculeName, String obScore, String canonicalSmiles, String alias, String pubchemId,String drugbankId,String casId, String moleculeWeight, Date createTime, Date updateTime) {
        this.acmpdId = acmpdId;
        this.moleculeName = moleculeName;
        this.obScore = obScore;
        this.canonicalSmiles = canonicalSmiles;
        this.alias = alias;
        this.pubchemId = pubchemId;
        this.casId = casId;
        this.moleculeWeight = moleculeWeight;
        this.drugbankId = drugbankId;

    }


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


    public String getDrugbankId() {
        return drugbankId;
    }

    public void setDrugbankId(String drugbankId) {
        this.drugbankId = drugbankId;
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

    public String getObScore() {
        return obScore;
    }

    public void setObScore(String obScore) {
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

    public String getPubchemId() {
        return pubchemId;
    }

    public void setPubchemId(String pubchemId) {
        this.pubchemId = pubchemId;
    }

    public String getCasId() {
        return casId;
    }

    public void setCasId(String casId) {
        this.casId = casId == null ? null : casId.trim();
    }

    public String getMoleculeWeight() {
        return moleculeWeight;
    }

    public void setMoleculeWeight(String moleculeWeight) {
        this.moleculeWeight = moleculeWeight;
    }


}