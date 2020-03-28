package com.hdi.hdi.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class FormulaHerb {
    private String formulaHerbId;

    private String formulaId;

    private String herbId;


    private String herbQuantity;

    private String herbQuantityUnit;

    private String source;



    public FormulaHerb(String formulaHerbId, String formulaId, String herbId,  String herbQuantity, String herbQuantityUnit, String source, Date createTime, Date updateTime) {
        this.formulaHerbId = formulaHerbId;
        this.formulaId = formulaId;
        this.herbId = herbId;

        this.herbQuantity = herbQuantity;
        this.herbQuantityUnit = herbQuantityUnit;
        this.source = source;

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



    public String getHerbQuantity() {
        return herbQuantity;
    }

    public void setHerbQuantity(String herbQuantity) {
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


}