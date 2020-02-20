package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.TargetCompound;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TargetCompoundMapper {
    int deleteByPrimaryKey(String targetCmpdId);

    int insert(TargetCompound record);

    int insertSelective(TargetCompound record);

    TargetCompound selectByPrimaryKey(String targetCmpdId);

    int updateByPrimaryKeySelective(TargetCompound record);

    int updateByPrimaryKey(TargetCompound record);

    List<TargetCompound> selectTargetCompoundListByAcmpdId(@Param("acmpdId") String acmpdId, @Param("page") int page);

    TargetCompound selectTargetCompoundByAcmpdId(String acmpdId ,String targetId);
}