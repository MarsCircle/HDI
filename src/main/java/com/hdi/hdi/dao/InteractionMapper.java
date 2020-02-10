package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.Interaction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface InteractionMapper {
    int deleteByPrimaryKey(String herbDrugId);

    int insert(Interaction record);

    int insertSelective(Interaction record);

    Interaction selectByPrimaryKey(String herbDrugId);

    int updateByPrimaryKeySelective(Interaction record);

    int updateByPrimaryKey(Interaction record);
}