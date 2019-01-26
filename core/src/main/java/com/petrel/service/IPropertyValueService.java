package com.petrel.service;

import com.petrel.domain.PropertyValue;

import java.util.List;


public interface IPropertyValueService {


    /**
     * 通过属性的id获取属性值
     */
    List<PropertyValue> getProListByProId(Long propertyId);

    /**
     * 保存属性值
     * @param propertyValueList
     */
    void save(List<PropertyValue> propertyValueList);

    /**
     * 删除指定id的属性值
     * @param id
     */
    void delete(Long id);
}
