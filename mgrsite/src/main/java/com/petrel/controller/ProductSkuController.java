package com.petrel.controller;


import com.petrel.service.IProductService;
import com.petrel.service.IProductSkuService;
import com.petrel.service.ISkuPropertyService;
import com.petrel.service.ISkuPropertyValueService;
import com.petrel.vo.GenerateVo;
import com.petrel.vo.JSONResult;
import com.petrel.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping(value = "/skus")
public class ProductSkuController {

    @Autowired
    private IProductService productService;

    @Autowired
    private ISkuPropertyService skuPropertyService;

    @Autowired
    private ISkuPropertyValueService skuPropertyValueService;

    @Autowired
    private IProductSkuService productSkuService;


    /**
     * /skus/generator
     * POST
     * 生成sku
     * @param vo
     * @param map
     * @return
     */
    @PostMapping(value = "/generator")
    public String generatorSku(@RequestBody GenerateVo vo, Map map){

        List<Map<String, Object>> skuList = productSkuService.generateSku(vo);
        map.put("skuProList",vo.getSkuPropertyList());
        map.put("skuList",skuList);
        return "product_sku/sku_form";
    }

    /**
     * /skus
     * 保存sku
     * @param vo
     * @return
     */
    @PostMapping(value = "")
    @ResponseBody
    public JSONResult save(ProductVo vo){
        JSONResult jsonResult = new JSONResult();
        productSkuService.save(vo);

        return jsonResult;
    }
}
