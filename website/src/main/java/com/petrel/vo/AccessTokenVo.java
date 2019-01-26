package com.petrel.vo;

import java.util.Date;

/**
 * Created by luohaipeng on 2017/11/1.
 */
public class AccessTokenVo {

    private String access_token;
    private Long expires_in;
    private Long createTime;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Long expires_in) {
        this.expires_in = expires_in;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Boolean isValid(){

        if(createTime == null){
            return false;
        }
        return (expires_in * 1000 + createTime) > new Date().getTime();
    }
}
