package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.HDInteraction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HDInteractionMapper {
    int deleteByPrimaryKey(String herbDrugId);

    int insert(HDInteraction record);

    int insertSelective(HDInteraction record);

    HDInteraction selectByPrimaryKey(String herbDrugId);

    int updateByPrimaryKeySelective(HDInteraction record);

    int updateByPrimaryKey(HDInteraction record);

    HDInteraction selectHDInteraction(String herbId, String drugId);
}