package com.petrel.service;

import com.petrel.domain.UserLogin;


public interface IUserLoginService {

    /**
     * 用户注册接口
     * @param user
     */
    void register(UserLogin user);

    /**
     * 判断用户名是否存在
     */
    Boolean isExists(String userName);

    /**
     * 用户登录
     */
    String login(String userName, String password);

    /**
     * 用户注销
     */
    void logout(String token);

}
