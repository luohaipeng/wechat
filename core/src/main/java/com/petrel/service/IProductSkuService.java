package com.petrel.service;

import com.petrel.domain.ProductSku;
import com.petrel.vo.GenerateVo;
import com.petrel.vo.ProductVo;

import java.util.List;
import java.util.Map;


public interface IProductSkuService
{

    /**
     * 生成sku
     * @param vo
     * @return
     */
    List<Map<String,Object>> generateSku(GenerateVo vo);

    void save(ProductVo vo);

    /**
     * 通过商品id获取该商品的sku
     * @param productId
     * @return
     */
    List<ProductSku> getProductSku(Long productId);

    /**
     * 同skuId获取sku对象
     * @param skuId
     * @return
     */
    ProductSku getById(Long skuId);
}
