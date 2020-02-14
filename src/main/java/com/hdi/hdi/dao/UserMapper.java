package com.hdi.hdi.dao;

import com.hdi.hdi.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Long userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    int checkEmail(@Param("email") String email);

    User selectLogin(@Param("email") String email);

    User selectEmail(@Param("email") String email);

    void activateByEmail(@Param("email") String email);

}