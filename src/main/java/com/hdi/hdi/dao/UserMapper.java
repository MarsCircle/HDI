package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername( String username);

    int checkEmail(@Param("email") String email);

    User selectLogin(@Param("username") String username);

    User selectEmail(@Param("email") String email);

    int activateByEmail(@Param("email") String email);

//    String selectPassword(@Param("username") String username);

}