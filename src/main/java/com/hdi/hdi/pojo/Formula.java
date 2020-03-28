package com.hdi.hdi.pojo;

import java.util.Date;

public class Formula {
    private String formulaId;

    private String formulaName;

    private String classic;

    private String group;

    private String subGroup;

    private String source;



    private  String formulaCompose;

    public Formula(String formulaId, String formulaName, String classic, String group, String subGroup, String source, Date createTime, Date updateTime) {
        this.formulaId = formulaId;
        this.formulaName = formulaName;
        this.classic = classic;
        this.group = group;
        this.subGroup = subGroup;
        this.source = source;

    }

    public Formula() {
        super();
    }

    public String getFormulaId() {
        return formulaId;
    }

    public void setFormulaId(String formulaId) {
        this.formulaId = formulaId == null ? null : formulaId.trim();
    }

    public String getFormulaName() {
        return formulaName;
    }

    public void setFormulaName(String formulaName) {
        this.formulaName = formulaName == null ? null : formulaName.trim();
    }

    public String getClassic() {
        return classic;
    }

    public void setClassic(String classic) {
        this.classic = classic == null ? null : classic.trim();
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group == null ? null : group.trim();
    }

    public String getSubGroup() {
        return subGroup;
    }

    public void setSubGroup(String subGroup) {
        this.subGroup = subGroup == null ? null : subGroup.trim();
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }


    public void setformulaCompose(String formulaCompose1) {
    }
}