package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.Compound;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompoundMapper {
    int deleteByPrimaryKey(String acmpdId);

    int insert(Compound record);

    int insertSelective(Compound record);

    Compound selectByPrimaryKey(String acmpdId);

    int updateByPrimaryKeySelective(Compound record);

    int updateByPrimaryKey(Compound record);

    String selectMoleculeName(String acmpdId);
}