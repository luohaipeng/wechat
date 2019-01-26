package com.petrel.service;

import com.petrel.domain.SkuProperty;

import java.util.List;


public interface ISkuPropertyService {

    /**
     * 通过分类ID获取该分类下的所有属性
     * @return
     */
    List<SkuProperty> getPropertyByCatalogId(Long id);


    /**
     * 保存属性对象
     * @param property
     */
    void save(SkuProperty property);

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
    List<SkuProperty> getProValByCatalogId(Long catalogId);

    /**
     * 通过sku属性id获取sku属性对象
     * @param skuPropertyId
     * @return
     */
    SkuProperty getSkuPropertyById(Long skuPropertyId);
}




