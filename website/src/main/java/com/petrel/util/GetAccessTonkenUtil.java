package com.petrel.util;

import com.petrel.vo.AccessTokenVo;
import com.alibaba.fastjson.JSON;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;


public class GetAccessTonkenUtil {

    //添加accessToken的静态成员变量，用作全局唯一的缓存
    private static AccessTokenVo accessTokenVo;

    private static Properties p = new Properties();

    //用静态代码块初始化该静态成员变量
    static {
        InputStream in = GetAccessTonkenUtil.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            p.load(in);

        }catch (Exception e){
            e.printStackTrace();
        }
        refresh();
    }

    //重新加载AccessToken
    private static void refresh() {

        String appid = (String) p.get("wechat.appid");
        String secret = (String) p.get("wechat.secret");
        //获取access_token的接口
        String getAccessTokenUrl = UrlUtil.ACCESS_TOKEN_URL.replace("APPID",appid).replace("APPSECRET",secret);

        String resultJson = HttpUtil.get(getAccessTokenUrl);

        accessTokenVo = JSON.parseObject(resultJson, AccessTokenVo.class);

        accessTokenVo.setCreateTime(new Date().getTime());
    }

    //添加获取accesstoken的静态方法
    public static AccessTokenVo getAccessToken(){

        if(!accessTokenVo.isValid()){
            refresh();
        }
        return accessTokenVo;
    }
}
