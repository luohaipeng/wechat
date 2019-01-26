package com.petrel.service;

import com.petrel.domain.Product;
import com.petrel.domain.ProductDetails;
import com.petrel.qo.PageResult;
import com.petrel.qo.QueryObject;
import com.petrel.vo.ProductVo;

import java.util.List;


public interface IProductService {

    List<Product> getAllProduct();

    PageResult productPage(QueryObject qo);

    /**
     * 保存商品
     */
    void save(ProductVo productVo);

    /**
     * 通过商品id获取商品对象
     * @param productId
     * @return
     */
    Product getProductById(Long productId);

    /**
     * 通过商品id获取整个商品对象(包含其他跟商品对象有关联的)
     * @param productId
     * @return
     */
    ProductVo getProductVo(Long productId);

    /**
     * 通过商品id获取商品详细介绍
     * @param productId
     * @return
     */
    ProductDetails getProductDetail(Long productId);
}
