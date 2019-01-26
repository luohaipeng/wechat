package com.petrel.controller;


import com.petrel.domain.PropertyValue;
import com.petrel.service.IPropertyValueService;
import com.petrel.vo.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by luohaipeng on 2018/3/10.
 */
@Controller
@RequestMapping("/propertyValues")
public class PropertyValueController {

    @Autowired
    private IPropertyValueService propertyValueService;

    /**
     * /propertyValues
     * 新增或修改属性值
     * @param propertyValueList
     * @return
     */
    @PostMapping(value = "")
    @ResponseBody
    public JSONResult save(@RequestBody List<PropertyValue> propertyValueList){
        JSONResult jsonResult = new JSONResult();
        propertyValueService.save(propertyValueList);
        return jsonResult;
    }

    /**
     * /propertyValues/{id}
     * DELETE
     * 删除指定id的属性值
     */
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public JSONResult method(@PathVariable("id") Long id){
        JSONResult jsonResult = new JSONResult();
        propertyValueService.delete(id);
        return jsonResult;
    }
}




















