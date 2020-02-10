package com.hdi.hdi.pojo;

import java.util.Date;

public class Herb {
    private String herbId;

    private String chineseSimplified;

    private String chineseTraditional;

    private String pinyinName;

    private String latinName;

    private String englishName;

    private String properties;

    private String meridians;

    private String function;

    private String classChinese;

    private String classEnglish;

    private String usePart;

    private Date createTime;

    private Date updateTime;

    public Herb(String herbId, String chineseSimplified, String chineseTraditional, String pinyinName, String latinName, String englishName, String properties, String meridians, String function, String classChinese, String classEnglish, String usePart, Date createTime, Date updateTime) {
        this.herbId = herbId;
        this.chineseSimplified = chineseSimplified;
        this.chineseTraditional = chineseTraditional;
        this.pinyinName = pinyinName;
        this.latinName = latinName;
        this.englishName = englishName;
        this.properties = properties;
        this.meridians = meridians;
        this.function = function;
        this.classChinese = classChinese;
        this.classEnglish = classEnglish;
        this.usePart = usePart;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Herb() {
        super();
    }

    public String getHerbId() {
        return herbId;
    }

    public void setHerbId(String herbId) {
        this.herbId = herbId == null ? null : herbId.trim();
    }

    public String getChineseSimplified() {
        return chineseSimplified;
    }

    public void setChineseSimplified(String chineseSimplified) {
        this.chineseSimplified = chineseSimplified == null ? null : chineseSimplified.trim();
    }

    public String getChineseTraditional() {
        return chineseTraditional;
    }

    public void setChineseTraditional(String chineseTraditional) {
        this.chineseTraditional = chineseTraditional == null ? null : chineseTraditional.trim();
    }

    public String getPinyinName() {
        return pinyinName;
    }

    public void setPinyinName(String pinyinName) {
        this.pinyinName = pinyinName == null ? null : pinyinName.trim();
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName == null ? null : latinName.trim();
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName == null ? null : englishName.trim();
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties == null ? null : properties.trim();
    }

    public String getMeridians() {
        return meridians;
    }

    public void setMeridians(String meridians) {
        this.meridians = meridians == null ? null : meridians.trim();
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function == null ? null : function.trim();
    }

    public String getClassChinese() {
        return classChinese;
    }

    public void setClassChinese(String classChinese) {
        this.classChinese = classChinese == null ? null : classChinese.trim();
    }

    public String getClassEnglish() {
        return classEnglish;
    }

    public void setClassEnglish(String classEnglish) {
        this.classEnglish = classEnglish == null ? null : classEnglish.trim();
    }

    public String getUsePart() {
        return usePart;
    }

    public void setUsePart(String usePart) {
        this.usePart = usePart == null ? null : usePart.trim();
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