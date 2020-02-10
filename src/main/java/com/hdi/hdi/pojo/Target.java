package com.hdi.hdi.pojo;

import java.util.Date;

public class Target {
    private String targetId;

    private String acmpdId;

    private String targetCmpdId;

    private String geneSymbol;

    private String alias;

    private String uniprotId;

    private String species;

    private Date createTime;

    private Date updateTime;

    public Target(String targetId, String acmpdId, String targetCmpdId, String geneSymbol, String alias, String uniprotId, String species, Date createTime, Date updateTime) {
        this.targetId = targetId;
        this.acmpdId = acmpdId;
        this.targetCmpdId = targetCmpdId;
        this.geneSymbol = geneSymbol;
        this.alias = alias;
        this.uniprotId = uniprotId;
        this.species = species;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Target() {
        super();
    }

    public String getTargetId() {
        return targetId;
    }

    public void setTargetId(String targetId) {
        this.targetId = targetId == null ? null : targetId.trim();
    }

    public String getAcmpdId() {
        return acmpdId;
    }

    public void setAcmpdId(String acmpdId) {
        this.acmpdId = acmpdId == null ? null : acmpdId.trim();
    }

    public String getTargetCmpdId() {
        return targetCmpdId;
    }

    public void setTargetCmpdId(String targetCmpdId) {
        this.targetCmpdId = targetCmpdId == null ? null : targetCmpdId.trim();
    }

    public String getGeneSymbol() {
        return geneSymbol;
    }

    public void setGeneSymbol(String geneSymbol) {
        this.geneSymbol = geneSymbol == null ? null : geneSymbol.trim();
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }

    public String getUniprotId() {
        return uniprotId;
    }

    public void setUniprotId(String uniprotId) {
        this.uniprotId = uniprotId == null ? null : uniprotId.trim();
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species == null ? null : species.trim();
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