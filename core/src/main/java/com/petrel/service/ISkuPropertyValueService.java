package com.petrel.service;

import com.petrel.domain.SkuPropertyValue;

import java.util.List;


public interface ISkuPropertyValueService {


    /**
     * 通过属性的id获取属性值
     */
    List<SkuPropertyValue> getProListByProId(Long propertyId);

    /**
     * 保存属性值
     * @param propertyValueList
     */
    void save(List<SkuPropertyValue> propertyValueList);

    /**
     * 通过sku属性值id删除该属性值
     * @param id
     */
    void delete(Long id);
}
