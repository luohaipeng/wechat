package com.petrel.domain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter@Getter
public class Property extends BaseDomain {

    private static final long serialVersionUID = 1L;

    private Long catalogId;

    private String name;

    private Integer sort;

    private Byte type;

    private List<PropertyValue> propertyValueList;


    public String getJson(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",getId());
        jsonObject.put("catalogId",catalogId);
        jsonObject.put("name",name);
        jsonObject.put("sort",sort);
        jsonObject.put("type",type);
        return JSON.toJSONString(jsonObject);
    }
}