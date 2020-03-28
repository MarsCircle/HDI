package com.hdi.hdi.pojo;

import java.util.Date;

public class Target {
    private String targetId;



    private String geneSymbol;

    private String alias;

    private String uniprotId;

    private String species;

    private String herbName;
    private String drugName;

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





    public Target(String targetId,  String geneSymbol, String alias, String uniprotId, String species, Date createTime, Date updateTime) {
        this.targetId = targetId;
        this.geneSymbol = geneSymbol;
        this.alias = alias;
        this.uniprotId = uniprotId;
        this.species = species;

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


}