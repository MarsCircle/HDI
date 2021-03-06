package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.Activity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityMapper {
    int deleteByPrimaryKey(Long activityId);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Long activityId);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    Long selectByTitle(String title);
}