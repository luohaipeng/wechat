package com.petrel.service.impl;

import com.petrel.domain.UserAddress;
import com.petrel.mapper.UserAddressMapper;
import com.petrel.service.IUserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class UserAddressServiceImpl implements IUserAddressService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public UserAddress getById(Long id) {
        return userAddressMapper.selectByPrimaryKey(id);
    }
}
