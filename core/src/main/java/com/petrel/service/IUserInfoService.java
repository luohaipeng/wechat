package com.petrel.service;

import com.petrel.domain.UserInfo;


public interface IUserInfoService {

    UserInfo getById(Long userId);
}
