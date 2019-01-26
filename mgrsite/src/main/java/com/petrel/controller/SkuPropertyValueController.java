package com.petrel.controller;


import com.petrel.domain.SkuPropertyValue;
import com.petrel.service.ISkuPropertyValueService;
import com.petrel.vo.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/skuPropertyValues")
public class SkuPropertyValueController {

    @Autowired
    private ISkuPropertyValueService propertyValueService;


    /**
     * /skuPropertyValues
     * POST
     * 新增或修改sku属性值
     * @param propertyValueList
     * @return
     */
    @PostMapping(value = "")
    @ResponseBody
    public JSONResult save(@RequestBody List<SkuPropertyValue> propertyValueList){
        JSONResult jsonResult = new JSONResult();
        propertyValueService.save(propertyValueList);

        return jsonResult;
    }

    /**
     * /skuPropertyValues/{id}
     * DELETE
     * 删除指定id的sku属性值
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public JSONResult method(@PathVariable("id") Long id){
        JSONResult jsonResult = new JSONResult();
        propertyValueService.delete(id);
        return jsonResult;
    }
}
