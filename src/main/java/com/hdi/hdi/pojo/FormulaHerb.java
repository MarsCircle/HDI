package com.hdi.hdi.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class FormulaHerb {
    private String formulaHerbId;

    private String formulaId;

    private String herbId;

    private String formulaName;

    private String herbName;

    private BigDecimal herbQuantity;

    private String herbQuantityUnit;

    private String source;

    private Date createTime;

    private Date updateTime;

    public FormulaHerb(String formulaHerbId, String formulaId, String herbId, String formulaName, String herbName, BigDecimal herbQuantity, String herbQuantityUnit, String source, Date createTime, Date updateTime) {
        this.formulaHerbId = formulaHerbId;
        this.formulaId = formulaId;
        this.herbId = herbId;
        this.formulaName = formulaName;
        this.herbName = herbName;
        this.herbQuantity = herbQuantity;
        this.herbQuantityUnit = herbQuantityUnit;
        this.source = source;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public FormulaHerb() {
        super();
    }

    public String getFormulaHerbId() {
        return formulaHerbId;
    }

    public void setFormulaHerbId(String formulaHerbId) {
        this.formulaHerbId = formulaHerbId == null ? null : formulaHerbId.trim();
    }

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId == null ? null : formulaId.trim();
    }

    public String getHerbId() {
        return herbId;
    }

    public void setHerbId(String herbId) {
        this.herbId = herbId == null ? null : herbId.trim();
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName == null ? null : formulaName.trim();
    }

    public String getHerbName() {
        return herbName;
    }

    public void setHerbName(String herbName) {
        this.herbName = herbName == null ? null : herbName.trim();
    }

    public BigDecimal getHerbQuantity() {
        return herbQuantity;
    }

    public void setHerbQuantity(BigDecimal herbQuantity) {
        this.herbQuantity = herbQuantity;
    }

    public String getHerbQuantityUnit() {
        return herbQuantityUnit;
    }

    public void setHerbQuantityUnit(String herbQuantityUnit) {
        this.herbQuantityUnit = herbQuantityUnit == null ? null : herbQuantityUnit.trim();
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