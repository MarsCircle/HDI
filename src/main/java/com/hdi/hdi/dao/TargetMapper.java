package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.Target;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TargetMapper {
    int deleteByPrimaryKey(String targetId);

    int insert(Target record);

    int insertSelective(Target record);

    Target selectByPrimaryKey(String targetId);

    int updateByPrimaryKeySelective(Target record);

    int updateByPrimaryKey(Target record);

    String selectGeneSymbol(String targetId);

    int checkTarget(String geneSymbol);

    Target selectTarget(String geneSymbol, String species);
}