package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.Formula;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.text.Normalizer;
import java.util.List;
import java.util.Map;

@Mapper
public interface FormulaMapper {
    int deleteByPrimaryKey(String formulaId);

    int insert(Formula record);

    int insertSelective(Formula record);

    Formula selectByPrimaryKey(String formulaId);

    int updateByPrimaryKeySelective(Formula record);

    int updateByPrimaryKey(Formula record);

    int checkFormula(String formulaName);

    Formula selectFormula(@Param("formulaName") String formulaName, @Param("group") String group, @Param("subGroup") String subGroup);

    List<Map> selectHerbNameId(String herbOrFormulaName);
}