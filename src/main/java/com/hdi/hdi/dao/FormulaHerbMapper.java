package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.FormulaHerb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface FormulaHerbMapper {
    int deleteByPrimaryKey(String formulaHerbId);

    int insert(FormulaHerb record);

    int insertSelective(FormulaHerb record);

    FormulaHerb selectByPrimaryKey(String formulaHerbId);

    int updateByPrimaryKeySelective(FormulaHerb record);

    int updateByPrimaryKey(FormulaHerb record);

    List<Map> selectFormulaCompose(String formulaName);
}