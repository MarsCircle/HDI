package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.ActivityRegistration;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityRegistrationMapper {
    int deleteByPrimaryKey(Long activityRegistrationId);

    int insert(ActivityRegistration record);

    int insertSelective(ActivityRegistration record);

    int activityRegistration(ActivityRegistration activityRegistration);

    ActivityRegistration selectByPrimaryKey(Long activityRegistrationId);

    int updateByPrimaryKeySelective(ActivityRegistration record);

    int updateByPrimaryKey(ActivityRegistration record);

    int countRegisterNumber(Long activityId);
}