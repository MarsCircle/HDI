package com.hdi.hdi.pojo;

import java.util.Date;

public class Activity {
    private Long activityId;

    private String title;

    private Date time;

    private String location;

    private Date registrationDeadline;

    private String limitNumber;

    private String description;

    private Date createTime;

    private Date updateTime;

    public Activity(Long activityId, String title, Date time, String location, Date registrationDeadline, String limitNumber, String description, Date createTime, Date updateTime) {
        this.activityId = activityId;
        this.title = title;
        this.time = time;
        this.location = location;
        this.registrationDeadline = registrationDeadline;
        this.limitNumber = limitNumber;
        this.description = description;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Activity() {
        super();
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Date getRegistrationDeadline() {
        return registrationDeadline;
    }

    public void setRegistrationDeadline(Date registrationDeadline) {
        this.registrationDeadline = registrationDeadline;
    }

    public String getLimitNumber() {
        return limitNumber;
    }

    public void setLimitNumber(String limitNumber) {
        this.limitNumber = limitNumber == null ? null : limitNumber.trim();

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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