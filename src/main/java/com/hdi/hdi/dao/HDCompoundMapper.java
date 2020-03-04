package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.HDCompound;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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

    List<Map> selectId(String acmpdId);


    List<Map> selectByAcmpdId(String acmpId);
}