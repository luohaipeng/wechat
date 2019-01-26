package com.petrel.mapper;

import com.petrel.domain.UserAddress;

import java.util.List;

public interface UserAddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserAddress record);

    UserAddress selectByPrimaryKey(Long id);

    List<UserAddress> selectAll();

    int updateByPrimaryKey(UserAddress record);
}