package com.petrel.vo;

/**
 * Created by luohaipeng on 2017/10/31.
 */
public class TemplateValueVo {
    private String value;
    private String color;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public TemplateValueVo(String value, String color) {
        this.value = value;
        this.color = color;
    }
}
