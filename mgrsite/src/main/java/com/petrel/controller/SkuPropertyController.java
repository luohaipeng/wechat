package com.petrel.controller;


import com.petrel.domain.SkuProperty;
import com.petrel.domain.SkuPropertyValue;
import com.petrel.service.ICatalogService;
import com.petrel.service.ISkuPropertyService;
import com.petrel.service.ISkuPropertyValueService;
import com.petrel.vo.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/skuPropertys")
public class SkuPropertyController {

    @Autowired
    private ISkuPropertyService propertyService;

    @Autowired
    private ISkuPropertyValueService propertyValueService;

    @Autowired
    private ICatalogService catalogService;

    /**
     * sku属性管理界面
     * /skuPropertys/view
     * GET
     * @param map
     * @return
     */
    @GetMapping(value = "/view")
    public String property(Map map){

        map.put("allCatalog",catalogService.getCatalogCache());
        return "sku_property/property";
    }


    /**
     * /skuPropertys
     * POST
     * 新增或修改sku属性
     * @param property
     * @return
     */
    @PostMapping(value = "")
    @ResponseBody
    public JSONResult save(SkuProperty property){
        JSONResult jsonResult = new JSONResult();
        propertyService.save(property);
        return jsonResult;
    }

    /**
     * /skuPropertys/{id}
     * DELETE
     * 通过属性id删除对应的属性
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public JSONResult method(@PathVariable("id") Long id){
        JSONResult jsonResult = new JSONResult();
        propertyService.delete(id);
        return jsonResult;
    }

    /**
     * /skuPropertys/{id}/skuPropertyValues
     * GET
     * 获取某属性下的属性值
     * @param propertyId
     * @param map
     * @return
     */
    @GetMapping(value = "/{id}/skuPropertyValues")
    public String get(@PathVariable("id") Long propertyId, Map map){
        List<SkuPropertyValue> propertyValueList = propertyValueService.getProListByProId(propertyId);
        map.put("propertyValueList",propertyValueList);
        map.put("propertyId",propertyId);
        return "sku_property/property_value_list";
    }

    /**
     * 获取指定的sku属性下的sku属性值
     * /skuPropertys/{id}/skuPropertyValues/table
     * GET
     * @param skuPropertyId
     * @param map
     * @return
     */
    @GetMapping(value = "/{id}/skuPropertyValues/table")
    public String getSkuPropertyValue(@PathVariable("id") Long skuPropertyId,Map map){

        SkuProperty skuProperty = propertyService.getSkuPropertyById(skuPropertyId);
        List<SkuPropertyValue> skuPropertyValueList = propertyValueService.getProListByProId(skuPropertyId);
        map.put("skuProperty",skuProperty);
        map.put("skuPropertyValueList",skuPropertyValueList);
        return "product_sku/sku_property_value_table";
    }
}
