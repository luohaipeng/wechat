package com.petrel.vo;

import java.util.List;

/**
 * Created by luohaipeng on 2017/11/1.
 */
public class CustomMenuVo {

    private String type;
    private String name;
    private String key;
    private String url;
    private List<CustomMenuVo> sub_button;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<CustomMenuVo> getSub_button() {
        return sub_button;
    }

    public void setSub_button(List<CustomMenuVo> sub_button) {
        this.sub_button = sub_button;
    }

    /**
     *
     * @param type
     * @param name
     * @param key
     * @param url
     */
    public CustomMenuVo(String type, String name, String key, String url) {
        this.type = type;
        this.name = name;
        this.key = key;
        this.url = url;
    }
}
