package com.hdi.hdi.pojo;

import java.util.Date;

public class TargetCompound {
    private String targetCmpdId;

    private String targetId;

    private String acmpdId;

    private String targetClass;

    private String relation;

    private String species;

    private String reference;

    private Date createTime;

    private Date updateTime;

    public String getGeneSymbol() {
        return geneSymbol;
    }

    public void setGeneSymbol(String geneSymbol) {
        this.geneSymbol = geneSymbol;
    }

    public String getMoleculeName() {
        return moleculeName;
    }

    public void setMoleculeName(String moleculeName) {
        this.moleculeName = moleculeName;
    }

    private String geneSymbol ;

    private String moleculeName;

    public TargetCompound(String targetCmpdId, String targetId, String acmpdId, String targetClass, String relation, String species, String reference, Date createTime, Date updateTime) {
        this.targetCmpdId = targetCmpdId;
        this.targetId = targetId;
        this.acmpdId = acmpdId;
        this.targetClass = targetClass;
        this.relation = relation;
        this.species = species;
        this.reference = reference;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TargetCompound() {
        super();
    }

    public String getTargetCmpdId() {
        return targetCmpdId;
    }

    public void setTargetCmpdId(String targetCmpdId) {
        this.targetCmpdId = targetCmpdId == null ? null : targetCmpdId.trim();
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

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass == null ? null : targetClass.trim();
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation == null ? null : relation.trim();
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species == null ? null : species.trim();
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference == null ? null : reference.trim();
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