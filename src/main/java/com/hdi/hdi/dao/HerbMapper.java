package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.Herb;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HerbMapper {
    int deleteByPrimaryKey(String herbId);

    int insert(Herb record);

    int insertSelective(Herb record);

    Herb selectByPrimaryKey(String herbId);

    int updateByPrimaryKeySelective(Herb record);

    int updateByPrimaryKey(Herb record);

    int checkHerb(String chineseSimplified);

    Herb selectHerb(String chineseSimplified, String classChinese);

    String selectHerbId(String chineseSimplified, String classChinese);

    String selectHerbIdByName(String chineseSimplified);

    String selectHerbName(String herbId);
}