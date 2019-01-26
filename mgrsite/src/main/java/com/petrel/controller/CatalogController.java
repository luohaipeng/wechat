package com.petrel.controller;

import com.petrel.domain.Catalog;
import com.petrel.domain.Property;
import com.petrel.domain.SkuProperty;
import com.petrel.service.ICatalogService;
import com.petrel.service.IPropertyService;
import com.petrel.service.ISkuPropertyService;
import com.petrel.vo.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/catalogs")
public class CatalogController {

    @Autowired
    private ICatalogService catalogService;

    @Autowired
    private IPropertyService propertyService;

    @Autowired
    private ISkuPropertyService skuPropertyService;

    /**
     * /catalogs
     * GET
     * @return
     */
    @GetMapping(value = "")
    @ResponseBody
    public List<Catalog> getAllCatalog(){
        return catalogService.getAllCatalog();
    }

    /**
     * /catalogs/view
     * 分类管理界面
     * @param map
     * @return
     */
    @GetMapping(value = "/view")
    public String catalog(Map map){
        //List<Catalog> allCatalog = catalogService.getAllCatalog();
        String catalogCache = catalogService.getCatalogCache();
        map.put("allCatalog",catalogCache);
        return "catalog/catalog";
    }

    /**
     * /catalogs/{id}/childs
     * POST
     * 通过分类id获取该id下的子分类
     * @return
     */
    @PostMapping(value = "/{id}/childs")
    @ResponseBody
    public List<Catalog> child(@PathVariable("id") Long id){
        List<Catalog> catalogList = catalogService.getChild(id);
        return catalogList;
    }

    /**
     * /catalogs/{id}/childs
     * GET
     * 通过分类id获取该id下的子分类
     * @return
     */
    @GetMapping(value = "/{id}/childs")
    public String getChildCatalog(@PathVariable("id") Long catalogId, Map map){
        List<Catalog> catalogList = catalogService.getChildCount(catalogId);
        map.put("catalogList",catalogList);
        return "catalog/child_catalog";
    }

    /**
     * /catalogs
     * PATCH
     * 更新分类排序
     * @param ids
     * @return
     */
    @PatchMapping(value = "")
    @ResponseBody
    public JSONResult updateSort(@RequestBody List<Long> ids){
        JSONResult jsonResult = new JSONResult();
        catalogService.updateSort(ids);
        return jsonResult;
    }

    /**
     * /catalogs
     * POST
     * @param catalog
     * @return
     */
    @PostMapping(value = "")
    @ResponseBody
    public JSONResult save(Catalog catalog){
        JSONResult jsonResult = new JSONResult();
        catalogService.save(catalog);
        return jsonResult;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public JSONResult delete(@PathVariable("id") Long catalogId){
        JSONResult jsonResult = new JSONResult();
        catalogService.delete(catalogId);
        return jsonResult;
    }

    /**
     * /catalogs/{id}/propertys
     * GET
     *通过分类id获取分类属性
     * @return
     */
    @GetMapping(value = "/{id}/propertys")
    public String getPropertys(@PathVariable("id") Long catalogId, Map map){
        List<Property> propertyList = propertyService.getPropertyByCatalogId(catalogId);
        map.put("propertyList",propertyList);
        return "property/property_list";
    }
    /**
     * /catalogs/{id}/propertys
     * GET
     *通过分类id获取sku属性
     * @return
     */
    @GetMapping(value = "/{id}/skuPropertys")
    public String getskuPropertys(@PathVariable("id") Long catalogId, Map map){
        List<SkuProperty> propertyList = skuPropertyService.getPropertyByCatalogId(catalogId);
        map.put("propertyList",propertyList);
        return "sku_property/property_list";
    }

    /**
     * /catalogs/{id}/propertys/propertyValues
     * 通过分类Id获取该分类下的属性和属性值
     * @param catalogId
     * @return
     */
    @GetMapping(value = "/{id}/propertys/propertyValues")
    public String method(@PathVariable("id") Long catalogId,Map map){
        List<Property> propertyList = propertyService.getProValByCatalogId(catalogId);
        map.put("propertyList",propertyList);
        return "product/product_property_value";
    }
}





