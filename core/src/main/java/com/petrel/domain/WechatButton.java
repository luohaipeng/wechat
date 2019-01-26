package com.petrel.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter@Getter
public class WechatButton extends BaseDomain{
    private Long id;

    private String name;

    private Long pId;

    private List<WechatButton> sub_button;

    private String type;

    private String key;

    private String url;

    private Integer appid;

    private String pagepath;


}