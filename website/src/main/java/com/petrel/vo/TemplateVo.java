package com.petrel.vo;

import java.util.Map;

/**
 * Created by luohaipeng on 2017/10/31.
 */
public class TemplateVo {
    private String touser;
    private String template_id;
    private String url;
    private Map<String,TemplateValueVo> data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, TemplateValueVo> getData() {
        return data;
    }

    public void setData(Map<String, TemplateValueVo> data) {
        this.data = data;
    }
}
