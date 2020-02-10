package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.ContactUs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContactUsMapper {
    int deleteByPrimaryKey(String id);

    int insert(ContactUs record);

    int insertSelective(ContactUs record);

    ContactUs selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ContactUs record);

    int updateByPrimaryKey(ContactUs record);
}