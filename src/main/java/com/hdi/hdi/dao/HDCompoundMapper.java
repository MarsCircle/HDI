package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.HDCompound;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HDCompoundMapper {
    int deleteByPrimaryKey(String hdAcmpdId);

    int insert(HDCompound record);

    int insertSelective(HDCompound record);

    HDCompound selectByPrimaryKey(String hdAcmpdId);

    int updateByPrimaryKeySelective(HDCompound record);

    int updateByPrimaryKey(HDCompound record);

    String selectAcmpdId(String herbId);

    String selectAcmpdIdByDrugId(String drugId);
}