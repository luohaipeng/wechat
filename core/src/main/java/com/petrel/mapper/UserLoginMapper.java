package com.petrel.mapper;

import com.petrel.domain.UserLogin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserLoginMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLogin record);

    UserLogin selectByPrimaryKey(Long id);

    List<UserLogin> selectAll();

    int updateByPrimaryKey(UserLogin record);

    UserLogin isExists(String userName);

    UserLogin login(@Param("userName") String userName, @Param("password") String password);
}