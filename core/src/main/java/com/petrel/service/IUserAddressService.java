package com.petrel.service;

import com.petrel.domain.UserAddress;

/**
 * Created by luohaipeng on 2018/3/14.
 */
public interface IUserAddressService {

    /**
     * 通过id获取用户收货地址对象
     */
    UserAddress getById(Long id);
}
