package com.petrel.controller;


import com.petrel.domain.Property;
import com.petrel.domain.PropertyValue;
import com.petrel.service.ICatalogService;
import com.petrel.service.IPropertyService;
import com.petrel.service.IPropertyValueService;
import com.petrel.vo.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by luohaipeng on 2018/3/9.
 */
@Controller
@RequestMapping(value = "/propertys")
public class PropertyController {

    @Autowired
    private IPropertyService propertyService;

    @Autowired
    private ICatalogService catalogService;

    @Autowired
    private IPropertyValueService propertyValueService;

    /**
     * /propertys/view
     * GET
     * 分类属性管理界面
     * @param map
     * @return
     */
    @GetMapping(value = "/view")
    public String property(Map map){
        map.put("allCatalog",catalogService.getCatalogCache());
        return "property/property";
    }

    /**
     * /propertys
     * POST
     * 新增或修改分类属性
     * @param property
     * @return
     */
    @PostMapping(value = "")
    @ResponseBody
    public JSONResult save(Property property){
        JSONResult jsonResult = new JSONResult();
        propertyService.save(property);
        return jsonResult;
    }

    /**
     * /propertys/{id}
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
     * /propertys/{id}/propertyValues
     * GET
     * 获取某属性下的属性值
     * @param propertyId
     * @param map
     * @return
     */
    @GetMapping(value = "/{id}/propertyValues")
    public String get(@PathVariable("id") Long propertyId, Map map){
        List<PropertyValue> propertyValueList = propertyValueService.getProListByProId(propertyId);
        map.put("propertyValueList",propertyValueList);
        map.put("propertyId",propertyId);
        return "property/property_value_list";
    }


}
