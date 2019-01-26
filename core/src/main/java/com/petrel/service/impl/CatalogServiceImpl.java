package com.petrel.service.impl;

import com.alibaba.fastjson.JSON;
import com.petrel.domain.Catalog;
import com.petrel.mapper.CatalogMapper;
import com.petrel.service.ICatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class CatalogServiceImpl implements ICatalogService {

    @Autowired
    private CatalogMapper catalogMapper;

    /*@Autowired
    private RedisTemplate redisTemplate;*/

    @Override
    public List<Catalog> getAllCatalog() {


        return catalogMapper.selectAll();
    }

    @Override
    public List<Catalog> getChild(Long id) {

        return catalogMapper.getChild(id);
    }

    @Override
    public List<Catalog> getChildCount(Long id) {
        //通过分类的id获取该id下子分类
        List<Catalog> childList = catalogMapper.getChild(id);

        /*for(Catalog catalog : childList){
            //获取redis每个子分类的商品统计和属性统计（map）
            Integer propertyCount = (Integer) redisTemplate.opsForValue().get(RedisConstant.CATALOG_PROPERTY_COUNT.replace("CATALOGID",catalog.getId()+""));
            Integer productCount = (Integer) redisTemplate.opsForValue().get(RedisConstant.CATALOG_PRODUCT_COUNT.replace("CATALOGID",catalog.getId()+""));
            //判断map是否为空
            if(propertyCount == null){
                //如果为mull，重新去数据库统计该分类的商品统计和属性统计，并设置到redis中
                propertyCount = catalogMapper.countPropertyNumber(catalog.getId());
                productCount = catalogMapper.countProductNumber(catalog.getId());
                redisTemplate.opsForValue().set(RedisConstant.CATALOG_PROPERTY_COUNT.replace("CATALOGID",catalog.getId()+""),propertyCount);
                redisTemplate.opsForValue().set(RedisConstant.CATALOG_PRODUCT_COUNT.replace("CATALOGID",catalog.getId()+""),productCount);
            }
            //把商品统计数和属性统计数设置到该分类上
            catalog.setPropertyCount(propertyCount);
            catalog.setProductCount(productCount);
        }*/
        return childList;
    }


    @Override
    public void updateSort(List<Long> ids) {
        for(int i=0;i<ids.size();i++){
            Catalog catalog = catalogMapper.selectByPrimaryKey(ids.get(i));
            catalog.setSort(i+1);
            catalogMapper.updateByPrimaryKey(catalog);
        }

        //refreshCatalogCache();
    }

    @Override
    public void save(Catalog catalog) {
        //如果是新增分类
        if(catalog.getId() == null){
            //获取该新增的分类的父分类，把isParent属性该成1（true），并更新父分类
            Catalog catalogParent = catalogMapper.selectByPrimaryKey(catalog.getPId());
            if(catalogParent != null){
                catalogParent.setIsParent(new Byte("1"));
                catalogMapper.updateByPrimaryKey(catalogParent);
            }
            catalogMapper.insert(catalog);
        }else{
            catalogMapper.updateByPrimaryKey(catalog);
        }
        //refreshCatalogCache();
    }

    @Override
    public void delete(Long catalogId) {
        //通过分类id获取该分类对象
        Catalog catalog = catalogMapper.selectByPrimaryKey(catalogId);
        //获取父分类x对象
        Catalog parentCatalog = catalogMapper.selectByPrimaryKey(catalog.getPId());
        //删除该分类
        catalogMapper.deleteByPrimaryKey(catalogId);
        //判断父分类还有没有子分类
        if(parentCatalog != null){
            List<Catalog> childList = catalogMapper.getChild(parentCatalog.getId());
            if(childList.size() == 0){
                parentCatalog.setIsParent(new Byte("0"));
                catalogMapper.updateByPrimaryKey(parentCatalog);
            }
        }
        //refreshCatalogCache();
    }

    @Override
    public Integer countProductNumber(Long catalogId) {
        return catalogMapper.countProductNumber(catalogId);

    }

    @Override
    public Integer countPropertyNumber(Long catalogId) {
        return catalogMapper.countPropertyNumber(catalogId);
    }

    @Override
    public String getCatalogCache() {
       /* String catalogJson = (String) redisTemplate.opsForValue().get("catalog:all");
        if(catalogJson == null){
            catalogJson = refreshCatalogCache();

        }*/
        List<Catalog> allCatalog = getAllCatalog();
        return JSON.toJSONString(allCatalog);
    }

    /*private String refreshCatalogCache() {
        String catalogJson;List<Catalog> allCatalog = getAllCatalog();
        catalogJson = JSON.toJSONString(allCatalog);
        redisTemplate.opsForValue().set("catalog:all",catalogJson);
        return catalogJson;
    }*/
}
