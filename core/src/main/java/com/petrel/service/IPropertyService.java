package com.petrel.service;

import com.petrel.domain.Property;

import java.util.List;


public interface IPropertyService {

    /**
     * 通过分类ID获取该分类下的所有属性
     * @return
     */
    List<Property> getPropertyByCatalogId(Long id);


    /**
     * 保存属性对象
     * @param property
     */
    void save(Property property);

    /**
     * 通过属性的id删除属性
     * @param id
     */
    void delete(Long id);

    /**
     * 通过分类Id获取该分类下的属性和属性值
     * @param catalogId
     * @return
     */
    List<Property> getProValByCatalogId(Long catalogId);
}




