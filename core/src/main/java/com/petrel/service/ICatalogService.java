package com.petrel.service;

import com.petrel.domain.Catalog;

import java.util.List;


public interface ICatalogService {

    List<Catalog> getAllCatalog();

    /**
     * 通过id获取该id的子分类
     * @param id
     * @return
     */
    List<Catalog> getChild(Long id);

    /**
     * 通过id获取该id的子分类(带有统计功能的)
     * @param id
     * @return
     */
    List<Catalog> getChildCount(Long id);


    /**
     * 更新分类排序
     * @param ids
     */
    void updateSort(List<Long> ids);

    /**
     * 保存分类
     * @param catalog
     */
    void save(Catalog catalog);

    /**
     * 删除分类
     * @param catalogId
     */
    void delete(Long catalogId);

    /**
     * 统计分类下商品个数
     */
    Integer countProductNumber(Long catalogId);

    /**
     * 统计分类下的属性个数
     */
    Integer countPropertyNumber(Long catalogId);

    /**
     * 从缓存获取所有分类
     * @return
     */
    String getCatalogCache();
}
