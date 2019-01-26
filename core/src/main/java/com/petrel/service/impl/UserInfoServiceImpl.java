package com.petrel.service.impl;

import com.petrel.domain.UserInfo;
import com.petrel.mapper.UserInfoMapper;
import com.petrel.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by luohaipeng on 2018/3/14.
 */
@Service
@Transactional
public class UserInfoServiceImpl implements IUserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getById(Long userId) {
        return userInfoMapper.selectByPrimaryKey(userId);
    }
}
