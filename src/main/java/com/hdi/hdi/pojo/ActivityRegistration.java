package com.hdi.hdi.pojo;

import java.util.Date;

public class ActivityRegistration {
    private Long activityRegistrationId;

    private Long activityId;

    private String title;

    private String email;

    private Date createTime;

    private Date updateTime;

    public ActivityRegistration(Long activityRegistrationId, Long activityId, String title, String email, Date createTime, Date updateTime) {
        this.activityRegistrationId = activityRegistrationId;
        this.activityId = activityId;
        this.title = title;
        this.email = email;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ActivityRegistration( Long activityId, String title, String email) {
        this.activityId = activityId;
        this.title = title;
        this.email = email;
    }

    public ActivityRegistration() {
        super();
    }

    public Long getActivityRegistrationId() {
        return activityRegistrationId;
    }

    public void setActivityRegistrationId(Long activityRegistrationId) {
        this.activityRegistrationId = activityRegistrationId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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