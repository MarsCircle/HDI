package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.Drug;
import com.hdi.hdi.pojo.Herb;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DrugMapper {
    int deleteByPrimaryKey(String drugId);

    int insert(Drug record);

    int insertSelective(Drug record);

    Drug selectByPrimaryKey(String drugId);

    int updateByPrimaryKeySelective(Drug record);

    int updateByPrimaryKey(Drug record);

    int checkDrug(String drugName);

    List<Drug> selectDrug(String drugName, String drugIndication, String route);

    List<String> selectDrugId(String drugName, String drugIndication, String route);

    String selectDrugName(String drug_id);

    String selectDrugIdByName(String drugName);
}