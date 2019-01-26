package com.petrel.service.impl;

import com.petrel.domain.UserInfo;
import com.petrel.domain.UserLogin;
import com.petrel.exception.CodeMsg;
import com.petrel.exception.UserException;
import com.petrel.mapper.UserInfoMapper;
import com.petrel.mapper.UserLoginMapper;
import com.petrel.service.IUserLoginService;
import com.petrel.util.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;



@Service
@Transactional
public class UserLoginServiceImpl implements IUserLoginService {

    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    /*@Autowired
    RedisTemplate redisTemplate;*/

    @Override
    public void register(UserLogin userLogin){
        //判断该注册用户是否存在
        if(isExists(userLogin.getUserName())){
            throw new UserException(CodeMsg.USER_NAME_EXISTS);
        }else{
            userLogin.setState(new Byte("0"));
            userLogin.setPassword(MD5.encode(userLogin.getPassword()));
            userLoginMapper.insert(userLogin);
            UserInfo userInfo = new UserInfo();
            userInfo.setRegistTime(new Date());
            userInfo.setId(userLogin.getId());
            userInfoMapper.insert(userInfo);
        }

    }

    @Override
    public Boolean isExists(String userName) {
        UserLogin user = userLoginMapper.isExists(userName);
        return user == null ? false : true;
    }

    @Override
    public String login(String userName, String password){
        /*UserLogin userLogin = userLoginMapper.login(userName,MD5.encode(password));
        if(userLogin == null){
            throw new UserException(CodeMsg.USER_NAME_OR_PASSWORD_ERROR);
        }else{
            //创建token并保存在redis中，key为token，value为user对象
            String token = createToken(userLogin);
            //返回token给前端
            return token;
        }*/
        return null;
    }

    /*private String createToken(UserLogin userLogin) {
        String token = UUID.randomUUID().toString();
        redisTemplate.opsForValue().set(RedisConstant.USER_LOGIN_KEY+token,userLogin);
        redisTemplate.expire(RedisConstant.USER_LOGIN_KEY+token,30, TimeUnit.DAYS);
        return token;
    }*/

    @Override
    public void logout(String token) throws UserException {
        /*if(StringUtils.isEmpty(token)){
            throw new UserException(CodeMsg.USER_TOKEN_ERROR);
        }else{
            Object user = redisTemplate.opsForValue().get(RedisConstant.USER_LOGIN_KEY + token);
            if(user == null){
                throw new UserException(CodeMsg.USER_TIME_OUT);
            }else{
                redisTemplate.delete(RedisConstant.USER_LOGIN_KEY + token);
            }
        }*/
    }

}
